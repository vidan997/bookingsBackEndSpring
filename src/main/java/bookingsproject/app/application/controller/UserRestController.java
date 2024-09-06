/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.AuthenticationReponseDto;
import bookingsproject.app.application.dto.UserDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import bookingsproject.app.application.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Munja
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8100")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody UserDto request) {
        try {
            return ResponseEntity.ok(userService.signIn(request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserDto request) {
        try {
            return ResponseEntity.ok(userService.signUp(request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email is already taken!");
        }
    }
}
