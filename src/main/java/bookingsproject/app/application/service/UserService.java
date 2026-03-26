package bookingsproject.app.application.service;

import bookingsproject.app.application.dto.AuthenticationReponseDto;
import bookingsproject.app.application.dto.UserDto;

public interface UserService {

    AuthenticationReponseDto signUp(UserDto userDto) throws Exception;

    AuthenticationReponseDto signIn(UserDto userDto) throws Exception;
}
