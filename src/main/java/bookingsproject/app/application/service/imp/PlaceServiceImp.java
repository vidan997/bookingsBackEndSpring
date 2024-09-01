/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.converter.PlaceConverter;
import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.repository.PlaceRepository;
import bookingsproject.app.application.service.PlaceService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Munja
 */
@Service
@Transactional
public class PlaceServiceImp implements PlaceService{
    
    private final PlaceRepository placeRepository;
    
    private final PlaceConverter placeConverter;

    public PlaceServiceImp(PlaceRepository placeRepository, PlaceConverter placeConverter) {
        this.placeRepository = placeRepository;
        this.placeConverter = placeConverter;
    }

    @Override
    public List<PlaceDto> findAll() {
        return placeRepository.findAll().stream().map(entity -> {
            return placeConverter.toDto(entity);
        }).collect(Collectors.toList());
    }
    
    
    
}
