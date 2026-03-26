package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.config.jwt.service.JwtService;
import bookingsproject.app.application.converter.UserConverter;
import bookingsproject.app.application.dto.AuthenticationReponseDto;
import bookingsproject.app.application.dto.UserDto;
import bookingsproject.app.application.model.UserEntity;
import bookingsproject.app.application.repository.UserRepository;
import bookingsproject.app.application.service.UserService;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImp(
            UserRepository userRepository,
            UserConverter userConverter,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationReponseDto signUp(UserDto userDto) throws Exception {

        if (isBlank(userDto.getFirstName()) || isBlank(userDto.getLastName()) || isBlank(userDto.getPhone())) {
            throw new Exception("First name, last name and phone are required.");
        }

        UserEntity userEntity = userConverter.toEntity(userDto);

        UserEntity saved;
        try {
            saved = userRepository.save(userEntity);
        } catch (Exception e) {
            throw new Exception("Email already taken!");
        }

        String jwtToken = jwtService.generateToken(saved);
        long expiresInSeconds = jwtService.getExpiresInSeconds(jwtToken);

        return new AuthenticationReponseDto(
                saved.getEmail(),
                saved.getId(),
                jwtToken,
                expiresInSeconds,
                saved.getFirstName(),
                saved.getLastName(),
                saved.getPhone()
        );
    }

    @Override
    public AuthenticationReponseDto signIn(UserDto userDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        Optional<UserEntity> userEntityOpt = userRepository.findByEmail(userDto.getEmail());
        if (userEntityOpt.isEmpty()) {
            throw new Exception("User not found");
        }

        UserEntity user = userEntityOpt.get();
        String jwtToken = jwtService.generateToken(user);
        long expiresInSeconds = jwtService.getExpiresInSeconds(jwtToken);

        return new AuthenticationReponseDto(
                user.getEmail(),
                user.getId(),
                jwtToken,
                expiresInSeconds,
                user.getFirstName(),
                user.getLastName(),
                user.getPhone()
        );
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}


