/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.BookingDto;
import bookingsproject.app.application.model.BookingEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Munja
 */

@Component
public class BookingConverter implements GenericConverter<BookingDto, BookingEntity>{

    @Override
    public BookingEntity toEntity(BookingDto dto) {
        return new BookingEntity(dto.getId(), dto.getPlaceId(), dto.getUserMail(), dto.getFirstName(), dto.getLastName(), dto.getGuestNumber(), dto.getBookedFrom(), dto.getBookedTo(), dto.getPlaceTitle(), dto.getPlaceImage());
    }

    @Override
    public BookingDto toDto(BookingEntity entity) {
        return new BookingDto(entity.getId(), entity.getPlaceId(), entity.getUserMail(), entity.getFirstName(), entity.getLastName(), entity.getGuestNumber(), entity.getBookedFrom(), entity.getBookedTo(), entity.getPlaceTitle(), entity.getPlaceImage());
    }
    
}
