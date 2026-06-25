package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.RoomSeasonPriceDto;
import bookingsproject.app.application.model.RoomSeasonPriceEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomSeasonPriceConverter implements GenericConverter<RoomSeasonPriceDto, RoomSeasonPriceEntity> {

    @Override
    public RoomSeasonPriceEntity toEntity(RoomSeasonPriceDto dto) {
        return new RoomSeasonPriceEntity(
                dto.getId(),
                dto.getRoomId(),
                dto.getSeasonName(),
                dto.getDateFrom(),
                dto.getDateTo(),
                dto.getPricePerNight()
        );
    }

    @Override
    public RoomSeasonPriceDto toDto(RoomSeasonPriceEntity entity) {
        return new RoomSeasonPriceDto(
                entity.getId(),
                entity.getRoomId(),
                entity.getSeasonName(),
                entity.getDateFrom(),
                entity.getDateTo(),
                entity.getPricePerNight()
        );
    }
}