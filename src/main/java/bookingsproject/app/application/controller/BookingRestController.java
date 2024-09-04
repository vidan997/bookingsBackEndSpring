/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.BookingDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import bookingsproject.app.application.service.BookingService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Munja
 */
@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:8100")
public class BookingRestController {
    
    private final BookingService bookingService;

    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    @GetMapping("/get/all/{userMail}")
    public List<BookingDto> findAllBookings(@PathVariable String userMail){
        return bookingService.findByUserMail(userMail);
    }
    
    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody BookingDto bookingDto){
        BookingDto bookingdto = bookingService.save(bookingDto);
        return ResponseEntity.status(HttpStatus.OK).body(bookingdto);  
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws InvalidEntityException{
        bookingService.deleteById(id);
    }
    
}
