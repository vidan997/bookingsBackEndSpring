package bookingsproject.app.application.service.implementation;

import bookingsproject.app.application.converter.PlaceConverter;
import bookingsproject.app.application.converter.RoomConverter;
import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.dto.RoomDto;
import bookingsproject.app.application.model.PlaceEntity;
import bookingsproject.app.application.model.RoomEntity;
import bookingsproject.app.application.repository.PlaceRepository;
import bookingsproject.app.application.repository.RoomRepository;
import bookingsproject.app.application.service.FileStorageService;
import bookingsproject.app.application.service.PlaceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import java.text.SimpleDateFormat;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PlaceServiceImp(
            PlaceRepository placeRepository,
            PlaceConverter placeConverter,
            FileStorageService fileStorageService,
            RoomRepository roomRepository,
            RoomConverter roomConverter
    ) {
        this.placeRepository = placeRepository;
        this.placeConverter = placeConverter;
        this.fileStorageService = fileStorageService;
        this.roomRepository = roomRepository;
        this.roomConverter = roomConverter;
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

        if (rooms != null && !rooms.isEmpty()) {
            for (RoomDto roomDto : rooms) {
                roomDto.setPlaceid(saved.getId());
                RoomEntity roomEntity = roomConverter.toEntity(roomDto);
                roomRepository.save(roomEntity);
            }
        }

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

        List<RoomEntity> existingRooms = roomRepository.findByPlaceid(saved.getId());
        for (RoomEntity room : existingRooms) {
            roomRepository.delete(room);
        }

        if (rooms != null && !rooms.isEmpty()) {
            for (RoomDto roomDto : rooms) {
                roomDto.setPlaceid(saved.getId());
                RoomEntity roomEntity = roomConverter.toEntity(roomDto);
                roomRepository.save(roomEntity);
            }
        }

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

        List<RoomEntity> existingRooms = roomRepository.findByPlaceid(entity.getId());
        for (RoomEntity room : existingRooms) {
            roomRepository.delete(room);
        }

        placeRepository.delete(entity);
    }

    private PlaceDto mapPlaceWithRooms(PlaceEntity placeEntity) {
        PlaceDto dto = placeConverter.toDto(placeEntity);

        List<RoomDto> rooms = roomRepository.findByPlaceid(placeEntity.getId()).stream()
                .map(roomConverter::toDto)
                .collect(Collectors.toList());

        dto.setRooms(rooms);
        return dto;
    }

    private List<String> readJsonUrls(String json) throws Exception {
        if (json == null || json.isBlank()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(json, new TypeReference<List<String>>() {});
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