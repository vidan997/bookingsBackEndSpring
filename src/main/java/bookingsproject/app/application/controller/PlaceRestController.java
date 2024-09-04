/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.service.PlaceService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Munja
 */
@RestController
@RequestMapping("/place")
@CrossOrigin(origins = "http://localhost:8100")
public class PlaceRestController {

    private final PlaceService placeService;

    public PlaceRestController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/all")
    public List<PlaceDto> findAll() {
        return placeService.findAll();
    }
    
    @GetMapping("/get/all/{userMail}")
    public List<PlaceDto> findByUserId(@PathVariable String userMail){
        return placeService.findByUserMail(userMail);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<PlaceDto> placeDto = placeService.findById(id);
        if (placeDto.isPresent()) {
            //return ResponseEntity.ok(cityDto.get());
            return ResponseEntity.status(HttpStatus.OK).body(placeDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid city!");
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody PlaceDto placeDto) {
        try {
            PlaceDto placedto = placeService.save(placeDto);
            return ResponseEntity.status(HttpStatus.OK).body(placedto);
        } catch (EntityExistsException e) {
            // dodaj jos za datume da budu jedan ispred drugog
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Place already exists!");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody PlaceDto placeDto) {
        PlaceDto placedto = placeService.update(placeDto);
        return ResponseEntity.status(HttpStatus.OK).body(placedto);
    }

}
