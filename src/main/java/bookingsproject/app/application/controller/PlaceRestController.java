package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.dto.RoomDto;
import bookingsproject.app.application.service.PlaceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/place")
@CrossOrigin(origins = "http://localhost:4200")
public class PlaceRestController {

    private final PlaceService placeService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PlaceRestController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/all")
    public List<PlaceDto> findAll() {
        return placeService.findAll();
    }

    @GetMapping("/get/all/{userId}")
    public List<PlaceDto> findByUserId(@PathVariable String userId) {
        return placeService.findByUserMail(userId);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<PlaceDto> placeDto = placeService.findById(id);
        if (placeDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(placeDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid place!");
    }

    @PostMapping("/save-multipart")
    public ResponseEntity<Object> saveMultipart(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String avaiableFrom,
            @RequestParam String avaiableTo,
            @RequestParam String userId,
            @RequestParam int coverIndex,
            @RequestParam("images") List<MultipartFile> images,
            @RequestParam(value = "rooms", required = false) String roomsJson
    ) {
        try {
            List<RoomDto> rooms = roomsJson != null && !roomsJson.isBlank()
                    ? objectMapper.readValue(roomsJson, new TypeReference<List<RoomDto>>() {})
                    : List.of();

            PlaceDto placedto = placeService.saveMultipart(
                    title, description,
                    avaiableFrom, avaiableTo,
                    userId, images, coverIndex, rooms
            );
            return ResponseEntity.status(HttpStatus.OK).body(placedto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update-multipart")
    public ResponseEntity<Object> updateMultipart(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam int coverIndex,
            @RequestParam(value = "existingImages", required = false) List<String> existingImages,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @RequestParam(value = "rooms", required = false) String roomsJson
    ) {
        try {
            List<RoomDto> rooms = roomsJson != null && !roomsJson.isBlank()
                    ? objectMapper.readValue(roomsJson, new TypeReference<List<RoomDto>>() {})
                    : List.of();

            PlaceDto updated = placeService.updateMultipart(
                    id, title, description,
                    existingImages, images, coverIndex, rooms
            );
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            placeService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
