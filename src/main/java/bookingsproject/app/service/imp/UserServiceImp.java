/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.service.imp;

import bookingsproject.app.dto.UserDto;
import bookingsproject.app.service.UserService;
import jakarta.persistence.EntityExistsException;
import java.util.Optional;

/**
 *
 * @author Munja
 */
public class UserServiceImp implements UserService{

    @Override
    public Optional<UserDto> findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserDto save(UserDto userDto) throws EntityExistsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
