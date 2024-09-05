/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.AuthenticationReponseDto;
import bookingsproject.app.application.dto.UserDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import bookingsproject.app.application.service.UserService;
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
    public ResponseEntity<AuthenticationReponseDto> signIn(@RequestBody UserDto request){
        return null;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationReponseDto> signUp(@RequestBody UserDto request){
        return null;
    }
}
