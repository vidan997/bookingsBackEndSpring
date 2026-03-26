package bookingsproject.app.application.service;

import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.dto.RoomDto;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface PlaceService {

    List<PlaceDto> findAll();

    Optional<PlaceDto> findById(Long id);

    List<PlaceDto> findByUserMail(String userMail);

    PlaceDto saveMultipart(
            String title,
            String description,
            String avaiableFrom,
            String avaiableTo,
            String userId,
            List<MultipartFile> images,
            int coverIndex,
            List<RoomDto> rooms
    ) throws Exception;

    PlaceDto updateMultipart(
            Long id,
            String title,
            String description,
            List<String> existingImages,
            List<MultipartFile> newImages,
            int coverIndex,
            List<RoomDto> rooms
    ) throws Exception;

    void delete(Long id) throws Exception;
}
