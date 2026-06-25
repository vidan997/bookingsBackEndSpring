package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.converter.BookingConverter;
import bookingsproject.app.application.dto.BookingDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import bookingsproject.app.application.model.BookingEntity;
import bookingsproject.app.application.model.PlaceEntity;
import bookingsproject.app.application.model.RoomEntity;
import bookingsproject.app.application.model.RoomSeasonPriceEntity;
import bookingsproject.app.application.model.UserEntity;
import bookingsproject.app.application.repository.BookingRepository;
import bookingsproject.app.application.repository.PlaceRepository;
import bookingsproject.app.application.repository.RoomRepository;
import bookingsproject.app.application.repository.RoomSeasonPriceRepository;
import bookingsproject.app.application.repository.UserRepository;
import bookingsproject.app.application.service.BookingService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomSeasonPriceRepository roomSeasonPriceRepository;

    public BookingServiceImp(BookingRepository bookingRepository,
            BookingConverter bookingConverter,
            PlaceRepository placeRepository,
            UserRepository userRepository,
            RoomRepository roomRepository,
            RoomSeasonPriceRepository roomSeasonPriceRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingConverter = bookingConverter;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.roomSeasonPriceRepository = roomSeasonPriceRepository;
    }

    @Override
    public List<BookingDto> findByUserMail(String userMail) {
        return bookingRepository.findByUserid(userMail)
                .stream()
                .map(bookingConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> findByPlaceId(long placeId) {
        return bookingRepository.findByPlaceId(placeId)
                .stream()
                .map(bookingConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto save(BookingDto bookingDto) throws EntityExistsException {

        if (bookingDto.getBookedFrom() == null || bookingDto.getBookedTo() == null) {
            throw new EntityExistsException("Dates are required");
        }

        if (!bookingDto.getBookedFrom().before(bookingDto.getBookedTo())) {
            throw new EntityExistsException("Invalid date range");
        }

        Optional<PlaceEntity> placeOpt = placeRepository.findById(bookingDto.getPlaceId());
        if (placeOpt.isEmpty()) {
            throw new EntityExistsException("Place not found");
        }

        Optional<RoomEntity> roomOpt = roomRepository.findById(bookingDto.getRoomid());
        if (roomOpt.isEmpty()) {
            throw new EntityExistsException("Room not found");
        }

        PlaceEntity place = placeOpt.get();
        RoomEntity room = roomOpt.get();

        if (room.getPlaceid() != bookingDto.getPlaceId()) {
            throw new EntityExistsException("Selected room does not belong to selected place");
        }

        boolean overlaps = bookingRepository.existsOverlappingForRoom(
                bookingDto.getRoomid(),
                bookingDto.getBookedFrom(),
                bookingDto.getBookedTo()
        );

        if (overlaps) {
            throw new EntityExistsException("Selected room is already booked for chosen dates");
        }

        String ownerPhone = null;
        try {
            Long ownerId = Long.valueOf(place.getUserId());
            Optional<UserEntity> ownerOpt = userRepository.findById(ownerId);
            ownerPhone = ownerOpt.map(UserEntity::getPhone).orElse(null);
        } catch (Exception e) {
            ownerPhone = null;
        }

        double totalPrice = calculateTotalPrice(
                bookingDto.getRoomid(),
                bookingDto.getBookedFrom(),
                bookingDto.getBookedTo()
        );

        bookingDto.setPriceAtBooking(totalPrice);
        bookingDto.setRoomType(room.getRoomType());
        bookingDto.setPlaceTitle(place.getTitle());
        bookingDto.setPlaceImage(place.getImageUrl());
        bookingDto.setOwnerPhone(ownerPhone);

        BookingEntity booking = bookingRepository.save(bookingConverter.toEntity(bookingDto));
        return bookingConverter.toDto(booking);
    }

    @Override
    public void deleteById(Long id) throws InvalidEntityException {
        bookingRepository.deleteById(id);
    }

    private double calculateTotalPrice(Long roomId, java.util.Date bookedFrom, java.util.Date bookedTo) {
    LocalDate start = bookedFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate end = bookedTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    BigDecimal total = BigDecimal.ZERO;
    LocalDate current = start;

    while (current.isBefore(end)) {
        List<RoomSeasonPriceEntity> pricesForDay = roomSeasonPriceRepository
                .findAllByRoomIdAndDateFromLessThanEqualAndDateToGreaterThanEqual(roomId, current, current);

        if (pricesForDay.isEmpty()) {
            throw new EntityExistsException("No seasonal price found for date: " + current);
        }

        BigDecimal maxPrice = pricesForDay.stream()
                .map(RoomSeasonPriceEntity::getPricePerNight)
                .max(BigDecimal::compareTo)
                .orElseThrow(() -> new EntityExistsException("No seasonal price found for date"));

        total = total.add(maxPrice);
        current = current.plusDays(1);
    }

    return total.doubleValue();
}
}
