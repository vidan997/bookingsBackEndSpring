/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.model.PlaceEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Munja
 */
@Component
public class PlaceConverter implements GenericConverter<PlaceDto,PlaceEntity>{

    @Override
    public PlaceEntity toEntity(PlaceDto dto) {
        return new PlaceEntity(dto.getId(),dto.getTitle(),dto.getDescription(),dto.getImageUrl(),dto.getPrice(),dto.getAvaiableFrom(),dto.getAvaiableTo(),dto.getUserId());
    }

    @Override
    public PlaceDto toDto(PlaceEntity entity) {
        return new PlaceDto(entity.getId(),entity.getTitle(),entity.getDescription(),entity.getImageUrl(),entity.getPrice(),entity.getAvaiableFrom(),entity.getAvaiableTo(),entity.getUserId());
    }
    
}
