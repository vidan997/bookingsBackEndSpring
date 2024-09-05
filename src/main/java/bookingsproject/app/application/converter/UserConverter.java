/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.UserDto;
import bookingsproject.app.application.model.ApplicationEntity;
import bookingsproject.app.application.model.UserEntity;
import bookingsproject.app.application.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Munja
 */
@Component
public class UserConverter implements GenericConverter<UserDto, UserEntity>{

    private final PasswordEncoder passwordEncoder;

    public UserConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserEntity toEntity(UserDto dto) {
       return new UserEntity(0, dto.getEmail(), passwordEncoder.encode(dto.getPassword()), Role.USER);
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        return new UserDto(entity.getEmail(), entity.getPassword());
    }
    
}
