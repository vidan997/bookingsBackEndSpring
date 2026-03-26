package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.CapturePayPalOrderRequestDto;
import bookingsproject.app.application.dto.CapturePayPalOrderResponseDto;
import bookingsproject.app.application.dto.CreatePayPalOrderRequestDto;
import bookingsproject.app.application.dto.CreatePayPalOrderResponseDto;
import bookingsproject.app.application.service.PayPalService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paypal")
@CrossOrigin(origins = "http://localhost:4200")
public class PayPalRestController {

    private final PayPalService payPalService;

    public PayPalRestController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<Object> createOrder(@Valid @RequestBody CreatePayPalOrderRequestDto requestDto) {
        try {
            CreatePayPalOrderResponseDto response = payPalService.createOrder(requestDto);
            return ResponseEntity.ok(response);
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/capture-order")
    public ResponseEntity<Object> captureOrder(@Valid @RequestBody CapturePayPalOrderRequestDto requestDto) {
        try {
            CapturePayPalOrderResponseDto response = payPalService.captureOrder(requestDto);
            return ResponseEntity.ok(response);
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}