package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.model.PlaceEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PlaceConverter implements GenericConverter<PlaceDto, PlaceEntity> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PlaceEntity toEntity(PlaceDto dto) {
        String imageUrlsJson = null;

        try {
            if (dto.getImageUrls() != null) {
                imageUrlsJson = objectMapper.writeValueAsString(dto.getImageUrls());
            }
        } catch (Exception e) {
            imageUrlsJson = null;
        }

        PlaceEntity entity = new PlaceEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImageUrl(dto.getImageUrl()); 
        entity.setImageUrlsJson(imageUrlsJson); 
        entity.setAvaiableFrom(dto.getAvaiableFrom());
        entity.setAvaiableTo(dto.getAvaiableTo());
        entity.setUserId(dto.getUserId());
        return entity;
    }

    @Override
    public PlaceDto toDto(PlaceEntity entity) {
        List<String> imageUrls = Collections.emptyList();

        try {
            if (entity.getImageUrlsJson() != null && !entity.getImageUrlsJson().isBlank()) {
                imageUrls = objectMapper.readValue(entity.getImageUrlsJson(), new TypeReference<List<String>>() {});
            }
        } catch (Exception e) {
            imageUrls = Collections.emptyList();
        }

        PlaceDto dto = new PlaceDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImageUrl(entity.getImageUrl()); 
        dto.setImageUrls(imageUrls); 
        dto.setAvaiableFrom(entity.getAvaiableFrom());
        dto.setAvaiableTo(entity.getAvaiableTo());
        dto.setUserId(entity.getUserId());
        return dto;
    }
}
