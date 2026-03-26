/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookingsproject.app.application.converter;

import bookingsproject.app.application.dto.ApplicationDto;
import bookingsproject.app.application.model.ApplicationEntity;


/**
 *
 * @author student2
 */
public interface GenericConverter<D extends ApplicationDto, E extends ApplicationEntity> {

    E toEntity(D dto);

    D toDto(E entity);
}
