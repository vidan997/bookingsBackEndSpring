/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookingsproject.app.application.service;

import bookingsproject.app.application.dto.BookingDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import jakarta.persistence.EntityExistsException;
import java.util.List;

/**
 *
 * @author Munja
 */
public interface BookingService {
    
    List<BookingDto> findByUserMail(String userMail);
    
    BookingDto save(BookingDto bookingDto) throws EntityExistsException;
    
    void deleteById(Long id) throws InvalidEntityException;
    
}
