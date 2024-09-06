/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.config.jwt.service.JwtService;
import bookingsproject.app.application.converter.UserConverter;
import bookingsproject.app.application.dto.AuthenticationReponseDto;
import bookingsproject.app.application.dto.UserDto;
import bookingsproject.app.application.model.UserEntity;
import bookingsproject.app.application.repository.UserRepository;
import bookingsproject.app.application.service.UserService;
import jakarta.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 *
 * @author Munja
 */
@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImp(UserRepository userRepository, UserConverter userConverter, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationReponseDto signUp(UserDto userDto) throws Exception {
        UserEntity userEntity = userConverter.toEntity(userDto);
        UserEntity entity;
        try {
            entity = userRepository.save(userEntity);
        } catch (Exception e) {
            throw new Exception("Email already taken!");
        }

        String jwtToken = jwtService.generateToken(userEntity);
        Date expDate = jwtService.extractExpiration(jwtToken);
        return new AuthenticationReponseDto(entity.getUsername(), entity.getId(), jwtToken, expDate);
    }

    @Override
    public AuthenticationReponseDto signIn(UserDto userDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        Optional<UserEntity> userEntity = userRepository.findByEmail(userDto.getEmail());
        String jwtToken = jwtService.generateToken(userEntity.get());
        Date expDate = jwtService.extractExpiration(jwtToken);
        return new AuthenticationReponseDto(userEntity.get().getUsername(), userEntity.get().getId(), jwtToken, expDate);
    }

}
