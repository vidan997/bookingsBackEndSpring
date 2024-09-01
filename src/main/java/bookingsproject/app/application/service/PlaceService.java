/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookingsproject.app.application.service;

import bookingsproject.app.application.dto.PlaceDto;
import java.util.List;

/**
 *
 * @author Munja
 */
public interface PlaceService {
    
    List<PlaceDto> findAll();
}
