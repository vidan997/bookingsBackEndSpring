package bookingsproject.app.application.service.implementation;

import bookingsproject.app.application.dto.BookingDto;
import bookingsproject.app.application.dto.CapturePayPalOrderRequestDto;
import bookingsproject.app.application.dto.CapturePayPalOrderResponseDto;
import bookingsproject.app.application.dto.CreatePayPalOrderRequestDto;
import bookingsproject.app.application.dto.CreatePayPalOrderResponseDto;
import bookingsproject.app.application.model.PaymentHoldEntity;
import bookingsproject.app.application.model.PlaceEntity;
import bookingsproject.app.application.model.RoomEntity;
import bookingsproject.app.application.model.RoomSeasonPriceEntity;
import bookingsproject.app.application.repository.BookingRepository;
import bookingsproject.app.application.repository.PaymentHoldRepository;
import bookingsproject.app.application.repository.PlaceRepository;
import bookingsproject.app.application.repository.RoomRepository;
import bookingsproject.app.application.repository.RoomSeasonPriceRepository;
import bookingsproject.app.application.service.BookingService;
import bookingsproject.app.application.service.PayPalService;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class PayPalServiceImpl implements PayPalService {

    private final RoomRepository roomRepository;
    private final RoomSeasonPriceRepository roomSeasonPriceRepository;
    private final PlaceRepository placeRepository;
    private final BookingRepository bookingRepository;
    private final PaymentHoldRepository paymentHoldRepository;
    private final BookingService bookingService;

    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @Value("${paypal.base-url}")
    private String baseUrl;

    @Value("${paypal.currency}")
    private String currency;

    @Value("${paypal.rsd-to-eur-rate}")
    private double rsdToEurRate;

    @Value("${paypal.hold-minutes}")
    private int holdMinutes;

    public PayPalServiceImpl(
            RoomRepository roomRepository,
            RoomSeasonPriceRepository roomSeasonPriceRepository,
            PlaceRepository placeRepository,
            BookingRepository bookingRepository,
            PaymentHoldRepository paymentHoldRepository,
            BookingService bookingService
    ) {
        this.roomRepository = roomRepository;
        this.roomSeasonPriceRepository = roomSeasonPriceRepository;
        this.placeRepository = placeRepository;
        this.bookingRepository = bookingRepository;
        this.paymentHoldRepository = paymentHoldRepository;
        this.bookingService = bookingService;
    }

    @Override
    public CreatePayPalOrderResponseDto createOrder(CreatePayPalOrderRequestDto requestDto) {

        validateDates(requestDto.getBookedFrom(), requestDto.getBookedTo());

        PlaceEntity place = placeRepository.findById(requestDto.getPlaceId())
                .orElseThrow(() -> new RuntimeException("Place not found"));

        RoomEntity room = roomRepository.findById(requestDto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getPlaceid() != place.getId()) {
            throw new RuntimeException("Room does not belong to place");
        }

        long bookings = bookingRepository.countOverlappingBookingsForRoom(
                room.getId(),
                requestDto.getBookedFrom(),
                requestDto.getBookedTo()
        );

        long holds = paymentHoldRepository.countActiveOverlappingHolds(
                room.getId(),
                requestDto.getBookedFrom(),
                requestDto.getBookedTo(),
                new Date()
        );

        if (bookings > 0 || holds > 0) {
            throw new RuntimeException("Room is not available");
        }

        double totalRsd = calculateTotalRsd(
                room.getId(),
                requestDto.getBookedFrom(),
                requestDto.getBookedTo()
        );

        double totalEur = round2(totalRsd / rsdToEurRate);

        String token = getAccessToken();
        String orderId = createPayPalOrder(token, totalEur);

        PaymentHoldEntity hold = new PaymentHoldEntity();
        hold.setPaypalOrderId(orderId);
        hold.setPlaceId(place.getId());
        hold.setRoomId(room.getId());
        hold.setUserId(requestDto.getUserId());
        hold.setBookedFrom(requestDto.getBookedFrom());
        hold.setBookedTo(requestDto.getBookedTo());
        hold.setStatus("CREATED");
        hold.setCreatedAt(new Date());
        hold.setExpiresAt(new Date(System.currentTimeMillis() + holdMinutes * 60_000L));

        paymentHoldRepository.save(hold);

        return new CreatePayPalOrderResponseDto(orderId, totalEur);
    }

    @Override
    public CapturePayPalOrderResponseDto captureOrder(CapturePayPalOrderRequestDto requestDto) {

        PaymentHoldEntity hold = paymentHoldRepository.findByPaypalOrderId(requestDto.getPaypalOrderId())
                .orElseThrow(() -> new RuntimeException("Hold not found"));

        if (!"CREATED".equals(hold.getStatus())) {
            throw new RuntimeException("Invalid hold");
        }

        if (hold.getExpiresAt().before(new Date())) {
            hold.setStatus("EXPIRED");
            paymentHoldRepository.save(hold);
            throw new RuntimeException("Expired");
        }

        validateDates(hold.getBookedFrom(), hold.getBookedTo());

        RoomEntity room = roomRepository.findById(hold.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        long bookings = bookingRepository.countOverlappingBookingsForRoom(
                room.getId(),
                hold.getBookedFrom(),
                hold.getBookedTo()
        );

        long holds = paymentHoldRepository.countActiveOverlappingHolds(
                room.getId(),
                hold.getBookedFrom(),
                hold.getBookedTo(),
                new Date()
        );

        if (bookings > 0 || holds > 1) {
            throw new RuntimeException("Room is not available");
        }

        String token = getAccessToken();
        Map<String, Object> res = capturePayPalOrder(token, requestDto.getPaypalOrderId());

        if (!"COMPLETED".equals(res.get("status"))) {
            throw new RuntimeException("Payment failed");
        }

        String captureId = extractCaptureId(res);

        BookingDto dto = new BookingDto();
        dto.setPlaceId(hold.getPlaceId());
        dto.setRoomid(hold.getRoomId());
        dto.setUserid(hold.getUserId());
        dto.setFirstName(requestDto.getFirstName());
        dto.setLastName(requestDto.getLastName());
        dto.setBookedFrom(hold.getBookedFrom());
        dto.setBookedTo(hold.getBookedTo());
        dto.setPaypalOrderId(requestDto.getPaypalOrderId());
        dto.setPaypalCaptureId(captureId);

        BookingDto saved = bookingService.save(dto);

        hold.setStatus("COMPLETED");
        paymentHoldRepository.save(hold);

        return new CapturePayPalOrderResponseDto(
                saved,
                requestDto.getPaypalOrderId(),
                captureId,
                "COMPLETED"
        );
    }

    private void validateDates(Date bookedFrom, Date bookedTo) {
        if (bookedFrom == null || bookedTo == null) {
            throw new RuntimeException("Dates are required");
        }

        if (!bookedFrom.before(bookedTo)) {
            throw new RuntimeException("Invalid date range");
        }
    }

    private double calculateTotalRsd(Long roomId, Date from, Date to) {
        LocalDate start = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        BigDecimal total = BigDecimal.ZERO;
        LocalDate current = start;

        while (current.isBefore(end)) {
            List<RoomSeasonPriceEntity> pricesForDay = roomSeasonPriceRepository
                    .findAllByRoomIdAndDateFromLessThanEqualAndDateToGreaterThanEqual(
                            roomId,
                            current,
                            current
                    );

            if (pricesForDay.isEmpty()) {
                throw new RuntimeException("No seasonal price found for date: " + current);
            }

            BigDecimal maxPrice = pricesForDay.stream()
                    .map(RoomSeasonPriceEntity::getPricePerNight)
                    .max(BigDecimal::compareTo)
                    .orElseThrow(() -> new RuntimeException("No seasonal price found for date"));

            total = total.add(maxPrice);
            current = current.plusDays(1);
        }

        return total.doubleValue();
    }

    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder()
                .encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodedAuth);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                baseUrl + "/v1/oauth2/token",
                HttpMethod.POST,
                request,
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || responseBody.get("access_token") == null) {
            throw new RuntimeException("Could not get PayPal access token");
        }

        return responseBody.get("access_token").toString();
    }

    private String createPayPalOrder(String accessToken, double totalEur) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, Object> amount = new HashMap<>();
        amount.put("currency_code", currency);
        amount.put("value", String.format(Locale.US, "%.2f", totalEur));

        Map<String, Object> purchaseUnit = new HashMap<>();
        purchaseUnit.put("amount", amount);

        Map<String, Object> body = new HashMap<>();
        body.put("intent", "CAPTURE");
        body.put("purchase_units", List.of(purchaseUnit));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                baseUrl + "/v2/checkout/orders",
                HttpMethod.POST,
                request,
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || responseBody.get("id") == null) {
            throw new RuntimeException("Could not create PayPal order");
        }

        return responseBody.get("id").toString();
    }

    private Map<String, Object> capturePayPalOrder(String accessToken, String paypalOrderId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<String> request = new HttpEntity<>("{}", headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                baseUrl + "/v2/checkout/orders/" + paypalOrderId + "/capture",
                HttpMethod.POST,
                request,
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null) {
            throw new RuntimeException("Could not capture PayPal order");
        }

        return responseBody;
    }

    private String extractCaptureId(Map<String, Object> captureResponse) {
        Object purchaseUnitsObj = captureResponse.get("purchase_units");
        if (!(purchaseUnitsObj instanceof List<?> purchaseUnits) || purchaseUnits.isEmpty()) {
            return null;
        }

        Object firstPurchaseUnitObj = purchaseUnits.get(0);
        if (!(firstPurchaseUnitObj instanceof Map<?, ?> firstPurchaseUnit)) {
            return null;
        }

        Object paymentsObj = firstPurchaseUnit.get("payments");
        if (!(paymentsObj instanceof Map<?, ?> payments)) {
            return null;
        }

        Object capturesObj = payments.get("captures");
        if (!(capturesObj instanceof List<?> captures) || captures.isEmpty()) {
            return null;
        }

        Object firstCaptureObj = captures.get(0);
        if (!(firstCaptureObj instanceof Map<?, ?> firstCapture)) {
            return null;
        }

        Object idObj = firstCapture.get("id");
        return idObj != null ? idObj.toString() : null;
    }
}
