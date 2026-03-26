package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.BookingDto;
import bookingsproject.app.application.model.BookingEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter implements GenericConverter<BookingDto, BookingEntity> {

    @Override
    public BookingEntity toEntity(BookingDto dto) {
        return new BookingEntity(
                dto.getId(),
                dto.getPlaceId(),
                dto.getRoomid(),
                dto.getUserid(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBookedFrom(),
                dto.getBookedTo(),
                dto.getRoomType(),
                dto.getPriceAtBooking(),
                dto.getPlaceTitle(),
                dto.getPlaceImage(),
                dto.getOwnerPhone()
        );
    }

    @Override
    public BookingDto toDto(BookingEntity entity) {
        return new BookingDto(
                entity.getId(),
                entity.getPlaceId(),
                entity.getRoomid(),
                entity.getUserId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBookedFrom(),
                entity.getBookedTo(),
                entity.getRoomType(),
                entity.getPriceAtBooking(),
                entity.getPlaceTitle(),
                entity.getPlaceImage(),
                entity.getOwnerPhone()
        );
    }
}