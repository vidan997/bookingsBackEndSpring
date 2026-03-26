/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookingsproject.app.application.repository;

import bookingsproject.app.application.model.PlaceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Munja
 */
@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long>{
    
    Optional<PlaceEntity> findByTitle(String title);
    
    List<PlaceEntity> findByUserid(String userMail);
    
}
