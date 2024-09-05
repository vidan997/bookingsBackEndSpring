/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.converter.UserConverter;
import bookingsproject.app.application.repository.UserRepository;
import bookingsproject.app.application.service.UserService;
import jakarta.transaction.Transactional;
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

    public UserServiceImp(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

}
