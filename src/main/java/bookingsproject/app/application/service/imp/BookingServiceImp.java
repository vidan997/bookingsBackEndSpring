/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.converter.BookingConverter;
import bookingsproject.app.application.dto.BookingDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import bookingsproject.app.application.model.BookingEntity;
import bookingsproject.app.application.model.PlaceEntity;
import bookingsproject.app.application.repository.BookingRepository;
import bookingsproject.app.application.service.BookingService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Munja
 */
@Service
@Transactional
public class BookingServiceImp implements BookingService{
    
    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;

    public BookingServiceImp(BookingRepository bookingRepository, BookingConverter bookingConverter) {
        this.bookingRepository = bookingRepository;
        this.bookingConverter = bookingConverter;
    }

    @Override
    public List<BookingDto> findByUserMail(String userMail) {
        return bookingRepository.findByUserMail(userMail).stream().map(entity -> {
            return bookingConverter.toDto(entity);
        }).collect(Collectors.toList());}

    @Override
    public BookingDto save(BookingDto bookingDto) throws EntityExistsException {
        BookingEntity booking = bookingRepository.save(bookingConverter.toEntity(bookingDto));
        return bookingConverter.toDto(booking);}

    @Override
    public void deleteById(Long id) throws InvalidEntityException {
        bookingRepository.deleteById(id);
    }
    
    
    
}
