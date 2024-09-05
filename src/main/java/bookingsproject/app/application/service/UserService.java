/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookingsproject.app.application.service;

import bookingsproject.app.application.converter.UserConverter;
import bookingsproject.app.application.dto.AuthenticationReponseDto;
import bookingsproject.app.application.dto.UserDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import bookingsproject.app.application.model.UserEntity;
import bookingsproject.app.application.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import java.util.Optional;

/**
 *
 * @author Munja
 */

public interface UserService {

    public AuthenticationReponseDto signUp(UserDto userDto);
    
    
    public AuthenticationReponseDto signIn(UserDto userDto);
    
    
}
