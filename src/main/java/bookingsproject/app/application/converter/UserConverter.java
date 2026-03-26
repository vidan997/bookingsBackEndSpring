package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.UserDto;
import bookingsproject.app.application.model.UserEntity;
import bookingsproject.app.application.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements GenericConverter<UserDto, UserEntity> {

    private final PasswordEncoder passwordEncoder;

    public UserConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        UserEntity e = new UserEntity();
        e.setEmail(dto.getEmail());
        e.setPassword(passwordEncoder.encode(dto.getPassword()));
        e.setRole(Role.USER);
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setPhone(dto.getPhone());
        return e;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        return new UserDto(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
}
