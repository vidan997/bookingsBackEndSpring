package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.RoomDto;
import bookingsproject.app.application.model.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter implements GenericConverter<RoomDto, RoomEntity> {

    @Override
    public RoomEntity toEntity(RoomDto dto) {
        return new RoomEntity(
                dto.getId(),
                dto.getPlaceid(),
                dto.getRoomType(),
                dto.getPrice(),
                dto.getQuantity()
        );
    }

    @Override
    public RoomDto toDto(RoomEntity entity) {
        return new RoomDto(
                entity.getId(),
                entity.getPlaceid(),
                entity.getRoomType(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }
}