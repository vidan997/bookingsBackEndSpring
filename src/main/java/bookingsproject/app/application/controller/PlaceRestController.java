/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.controller;

import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.service.PlaceService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Munja
 */
@RestController
@RequestMapping("/place")
public class PlaceRestController {
    
    
    private final PlaceService placeService;

    public PlaceRestController(PlaceService placeService) {
        this.placeService = placeService;
    }
    
    @GetMapping("/all")
    public List<PlaceDto> findAll(){
        return placeService.findAll();
    }
    
    
}
