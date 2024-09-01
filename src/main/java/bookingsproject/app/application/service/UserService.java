/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookingsproject.app.application.service;

import bookingsproject.app.application.dto.UserDto;
import jakarta.persistence.EntityExistsException;
import java.util.Optional;

/**
 *
 * @author Munja
 */
public interface UserService {
    
    Optional<UserDto> findByEmail(String email);
    
    UserDto save(UserDto userDto) throws EntityExistsException;
}
