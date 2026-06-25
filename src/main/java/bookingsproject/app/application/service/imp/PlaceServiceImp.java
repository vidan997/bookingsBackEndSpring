package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.converter.PlaceConverter;
import bookingsproject.app.application.converter.RoomConverter;
import bookingsproject.app.application.converter.RoomSeasonPriceConverter;
import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.dto.RoomDto;
import bookingsproject.app.application.dto.RoomSeasonPriceDto;
import bookingsproject.app.application.model.PlaceEntity;
import bookingsproject.app.application.model.RoomEntity;
import bookingsproject.app.application.model.RoomSeasonPriceEntity;
import bookingsproject.app.application.repository.PlaceRepository;
import bookingsproject.app.application.repository.RoomRepository;
import bookingsproject.app.application.repository.RoomSeasonPriceRepository;
import bookingsproject.app.application.service.FileStorageService;
import bookingsproject.app.application.service.PlaceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class PlaceServiceImp implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceConverter placeConverter;
    private final FileStorageService fileStorageService;
    private final RoomRepository roomRepository;
    private final RoomConverter roomConverter;
    private final RoomSeasonPriceRepository roomSeasonPriceRepository;
    private final RoomSeasonPriceConverter roomSeasonPriceConverter;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PlaceServiceImp(
            PlaceRepository placeRepository,
            PlaceConverter placeConverter,
            FileStorageService fileStorageService,
            RoomRepository roomRepository,
            RoomConverter roomConverter,
            RoomSeasonPriceRepository roomSeasonPriceRepository,
            RoomSeasonPriceConverter roomSeasonPriceConverter
    ) {
        this.placeRepository = placeRepository;
        this.placeConverter = placeConverter;
        this.fileStorageService = fileStorageService;
        this.roomRepository = roomRepository;
        this.roomConverter = roomConverter;
        this.roomSeasonPriceRepository = roomSeasonPriceRepository;
        this.roomSeasonPriceConverter = roomSeasonPriceConverter;
    }

    @Override
    public List<PlaceDto> findAll() {
        return placeRepository.findAll().stream()
                .map(this::mapPlaceWithRooms)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlaceDto> findById(Long id) {
        return placeRepository.findById(id).map(this::mapPlaceWithRooms);
    }

    @Override
    public List<PlaceDto> findByUserMail(String userMail) {
        return placeRepository.findByUserid(userMail).stream()
                .map(this::mapPlaceWithRooms)
                .collect(Collectors.toList());
    }

    @Override
    public PlaceDto saveMultipart(
            String title,
            String description,
            String avaiableFrom,
            String avaiableTo,
            String userId,
            List<MultipartFile> images,
            int coverIndex,
            List<RoomDto> rooms
    ) throws Exception {

        Optional<PlaceEntity> existing = placeRepository.findByTitle(title);
        if (existing.isPresent()) {
            throw new EntityExistsException("Place already exist!");
        }

        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("At least one image is required.");
        }

        List<String> urls = fileStorageService.saveAll(images);
        if (urls.isEmpty()) {
            throw new IllegalArgumentException("Images could not be saved.");
        }

        if (coverIndex < 0 || coverIndex >= urls.size()) {
            coverIndex = 0;
        }

        String coverUrl = urls.get(coverIndex);
        String imageUrlsJson = objectMapper.writeValueAsString(urls);

        Date fromDate = parseIsoDate(avaiableFrom);
        Date toDate = parseIsoDate(avaiableTo);

        PlaceEntity entity = new PlaceEntity();
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setAvaiableFrom(fromDate);
        entity.setAvaiableTo(toDate);
        entity.setUserId(userId);
        entity.setImageUrl(coverUrl);
        entity.setImageUrlsJson(imageUrlsJson);

        PlaceEntity saved = placeRepository.save(entity);

        saveRoomsAndSeasonPrices(saved.getId(), rooms);

        return mapPlaceWithRooms(saved);
    }

    @Override
    public PlaceDto updateMultipart(
            Long id,
            String title,
            String description,
            List<String> existingImages,
            List<MultipartFile> newImages,
            int coverIndex,
            List<RoomDto> rooms
    ) throws Exception {

        PlaceEntity entity = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place not found."));

        Optional<PlaceEntity> byTitle = placeRepository.findByTitle(title);
        if (byTitle.isPresent() && byTitle.get().getId() != id) {
            throw new EntityExistsException("Place already exist!");
        }

        List<String> oldUrls = readJsonUrls(entity.getImageUrlsJson());

        List<String> keep = existingImages != null ? new ArrayList<>(existingImages) : new ArrayList<>();
        List<String> added = (newImages != null && !newImages.isEmpty())
                ? fileStorageService.saveAll(newImages)
                : new ArrayList<>();

        List<String> finalUrls = new ArrayList<>();
        finalUrls.addAll(keep);
        finalUrls.addAll(added);

        if (finalUrls.isEmpty()) {
            throw new IllegalArgumentException("At least one image is required.");
        }

        for (String old : oldUrls) {
            if (!finalUrls.contains(old)) {
                fileStorageService.deleteByUrl(old);
            }
        }

        if (coverIndex < 0 || coverIndex >= finalUrls.size()) {
            coverIndex = 0;
        }

        entity.setTitle(title);
        entity.setDescription(description);
        entity.setImageUrl(finalUrls.get(coverIndex));
        entity.setImageUrlsJson(objectMapper.writeValueAsString(finalUrls));

        PlaceEntity saved = placeRepository.save(entity);

        deleteRoomsAndSeasonPrices(saved.getId());
        saveRoomsAndSeasonPrices(saved.getId(), rooms);

        return mapPlaceWithRooms(saved);
    }

    @Override
    public void delete(Long id) throws Exception {
        PlaceEntity entity = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place not found."));

        List<String> urls = readJsonUrls(entity.getImageUrlsJson());
        for (String u : urls) {
            fileStorageService.deleteByUrl(u);
        }

        deleteRoomsAndSeasonPrices(entity.getId());

        placeRepository.delete(entity);
    }

    private void saveRoomsAndSeasonPrices(Long placeId, List<RoomDto> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return;
        }

        for (RoomDto roomDto : rooms) {
            roomDto.setPlaceid(placeId);

            RoomEntity roomEntity = roomConverter.toEntity(roomDto);
            RoomEntity savedRoom = roomRepository.save(roomEntity);

            if (roomDto.getSeasonPrices() != null && !roomDto.getSeasonPrices().isEmpty()) {
                validateSeasonPrices(roomDto.getSeasonPrices());

                for (RoomSeasonPriceDto seasonPriceDto : roomDto.getSeasonPrices()) {
                    seasonPriceDto.setRoomId(savedRoom.getId());

                    RoomSeasonPriceEntity seasonPriceEntity = roomSeasonPriceConverter.toEntity(seasonPriceDto);
                    roomSeasonPriceRepository.save(seasonPriceEntity);
                }
            }
        }
    }

    private void validateSeasonPrices(List<RoomSeasonPriceDto> seasonPrices) {
        if (seasonPrices == null || seasonPrices.isEmpty()) {
            return;
        }

        for (RoomSeasonPriceDto seasonPrice : seasonPrices) {
            if (seasonPrice.getDateFrom() == null || seasonPrice.getDateTo() == null) {
                throw new IllegalArgumentException("Season price dates are required");
            }

            if (seasonPrice.getDateFrom().isAfter(seasonPrice.getDateTo())) {
                throw new IllegalArgumentException("Season price dateFrom must be before or equal to dateTo");
            }

            if (seasonPrice.getPricePerNight() == null) {
                throw new IllegalArgumentException("Season price per night is required");
            }

            if (seasonPrice.getPricePerNight().doubleValue() < 0) {
                throw new IllegalArgumentException("Season price per night must be greater than or equal to 0");
            }
        }
    }

    private void deleteRoomsAndSeasonPrices(Long placeId) {
        List<RoomEntity> existingRooms = roomRepository.findByPlaceid(placeId);

        for (RoomEntity room : existingRooms) {
            List<RoomSeasonPriceEntity> seasonPrices = roomSeasonPriceRepository.findByRoomId(room.getId());
            for (RoomSeasonPriceEntity seasonPrice : seasonPrices) {
                roomSeasonPriceRepository.delete(seasonPrice);
            }
            roomRepository.delete(room);
        }
    }

    private PlaceDto mapPlaceWithRooms(PlaceEntity placeEntity) {
        PlaceDto dto = placeConverter.toDto(placeEntity);

        List<RoomDto> rooms = roomRepository.findByPlaceid(placeEntity.getId()).stream()
                .map(room -> {
                    RoomDto roomDto = roomConverter.toDto(room);

                    List<RoomSeasonPriceDto> seasonPrices = roomSeasonPriceRepository.findByRoomId(room.getId())
                            .stream()
                            .map(roomSeasonPriceConverter::toDto)
                            .collect(Collectors.toList());

                    roomDto.setSeasonPrices(seasonPrices);
                    return roomDto;
                })
                .collect(Collectors.toList());

        dto.setRooms(rooms);
        return dto;
    }

    private List<String> readJsonUrls(String json) throws Exception {
        if (json == null || json.isBlank()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(json, new TypeReference<List<String>>() {
        });
    }

    private Date parseIsoDate(String iso) throws Exception {
        if (iso == null || iso.isBlank()) {
            return null;
        }

        if (iso.length() >= 10) {
            String onlyDate = iso.substring(0, 10);
            return new SimpleDateFormat("yyyy-MM-dd").parse(onlyDate);
        }
        return null;
    }
}
