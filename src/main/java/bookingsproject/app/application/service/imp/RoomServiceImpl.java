package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.converter.RoomConverter;
import bookingsproject.app.application.dto.RoomDto;
import bookingsproject.app.application.exception.ApplicationException;
import bookingsproject.app.application.model.RoomEntity;
import bookingsproject.app.application.repository.RoomRepository;
import bookingsproject.app.application.service.RoomService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomConverter roomConverter;

    public RoomServiceImpl(RoomRepository roomRepository,
                           RoomConverter roomConverter) {
        this.roomRepository = roomRepository;
        this.roomConverter = roomConverter;
    }

    @Override
    public RoomDto save(RoomDto roomDto) throws ApplicationException {
        RoomEntity entity = roomConverter.toEntity(roomDto);
        RoomEntity saved = roomRepository.save(entity);
        return roomConverter.toDto(saved);
    }

    @Override
    public List<RoomDto> findByPlaceid(long placeid) throws ApplicationException {
        List<RoomEntity> entities = roomRepository.findByPlaceid(placeid);
        List<RoomDto> result = new ArrayList<>();

        for (RoomEntity e : entities) {
            result.add(roomConverter.toDto(e));
        }

        return result;
    }

    @Override
    public List<RoomDto> saveAll(List<RoomDto> rooms) throws ApplicationException {
        List<RoomDto> result = new ArrayList<>();

        for (RoomDto roomDto : rooms) {
            RoomEntity entity = roomConverter.toEntity(roomDto);
            RoomEntity saved = roomRepository.save(entity);
            result.add(roomConverter.toDto(saved));
        }

        return result;
    }

    @Override
    public void deleteById(Long id) throws ApplicationException {
        roomRepository.deleteById(id);
    }
}