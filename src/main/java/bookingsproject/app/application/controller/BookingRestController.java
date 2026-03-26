package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.BookingDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import bookingsproject.app.application.service.BookingService;
import jakarta.persistence.EntityExistsException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingRestController {

    private final BookingService bookingService;

    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/get/all/{userMail}")
    public List<BookingDto> findAllBookings(@PathVariable String userMail) {
        return bookingService.findByUserMail(userMail);
    }

    @GetMapping("/get/place/{placeId}")
    public List<BookingDto> findAllBookingsForPlace(@PathVariable long placeId) {
        return bookingService.findByPlaceId(placeId);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody BookingDto bookingDto) {
        try {
            BookingDto saved = bookingService.save(bookingDto);
            return ResponseEntity.status(HttpStatus.OK).body(saved);
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws InvalidEntityException {
        bookingService.deleteById(id);
    }
}
