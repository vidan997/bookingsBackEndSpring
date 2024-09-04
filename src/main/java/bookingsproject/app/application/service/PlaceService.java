/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookingsproject.app.application.service;

import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import jakarta.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Munja
 */
public interface PlaceService {
    
    List<PlaceDto> findAll();
    
    Optional<PlaceDto> findById(Long id);

    PlaceDto save(PlaceDto placeDto) throws EntityExistsException;
    
    PlaceDto update(PlaceDto placeDto);
    
    List<PlaceDto> findByUserMail(String userMail);
}
