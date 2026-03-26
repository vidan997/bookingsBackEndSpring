package bookingsproject.app.application.service;

import bookingsproject.app.application.dto.RoomDto;
import bookingsproject.app.application.exception.ApplicationException;
import java.util.List;

public interface RoomService {

    RoomDto save(RoomDto roomDto) throws ApplicationException;

    List<RoomDto> findByPlaceid(long placeid) throws ApplicationException;

    List<RoomDto> saveAll(List<RoomDto> rooms) throws ApplicationException;

    void deleteById(Long id) throws ApplicationException;
}