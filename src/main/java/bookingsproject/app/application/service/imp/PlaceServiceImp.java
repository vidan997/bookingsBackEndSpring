/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.converter.PlaceConverter;
import bookingsproject.app.application.dto.PlaceDto;
import bookingsproject.app.application.exception.InvalidEntityException;
import bookingsproject.app.application.model.PlaceEntity;
import bookingsproject.app.application.repository.PlaceRepository;
import bookingsproject.app.application.service.PlaceService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.hibernate.query.results.Builders.entity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Munja
 */
@Service
@Transactional
public class PlaceServiceImp implements PlaceService {

    private final PlaceRepository placeRepository;

    private final PlaceConverter placeConverter;

    public PlaceServiceImp(PlaceRepository placeRepository, PlaceConverter placeConverter) {
        this.placeRepository = placeRepository;
        this.placeConverter = placeConverter;
    }

    @Override
    public List<PlaceDto> findAll() {
        return placeRepository.findAll().stream().map(entity -> {
            System.out.println(entity.getImageUrl());
            return placeConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<PlaceDto> findById(Long id) {
        Optional<PlaceEntity> entity = placeRepository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(placeConverter.toDto(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public PlaceDto save(PlaceDto placeDto) throws EntityExistsException {
        Optional<PlaceEntity> entity = placeRepository.findByTitle(placeDto.getTitle());
        if (entity.isPresent()) {
            throw new EntityExistsException("Place already exist!");
        }
        PlaceEntity place = placeRepository.save(placeConverter.toEntity(placeDto));
        return placeConverter.toDto(place);
    }

    @Override
    public PlaceDto update(PlaceDto placeDto) {
        PlaceEntity place = placeRepository.save(placeConverter.toEntity(placeDto));
        return placeConverter.toDto(place);
    }

    @Override
    public List<PlaceDto> findByUserMail(String userMail) {
        return placeRepository.findByUserMail(userMail).stream().map(entity -> {
            return placeConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

}
