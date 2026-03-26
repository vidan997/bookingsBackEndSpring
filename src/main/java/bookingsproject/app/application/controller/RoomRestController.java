package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.RoomDto;
import bookingsproject.app.application.exception.ApplicationException;
import bookingsproject.app.application.service.RoomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomRestController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/save")
    public RoomDto save(@RequestBody RoomDto roomDto) throws ApplicationException {
        return roomService.save(roomDto);
    }

    @PostMapping("/save/all")
    public List<RoomDto> saveAll(@RequestBody List<RoomDto> rooms) throws ApplicationException {
        return roomService.saveAll(rooms);
    }

    @GetMapping("/get/place/{placeid}")
    public List<RoomDto> getByPlaceid(@PathVariable long placeid) throws ApplicationException {
        return roomService.findByPlaceid(placeid);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws ApplicationException {
        roomService.deleteById(id);
    }
}