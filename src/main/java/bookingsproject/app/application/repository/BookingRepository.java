/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookingsproject.app.application.repository;

import bookingsproject.app.application.model.BookingEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Munja
 */
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long>{
    
    List<BookingEntity> findByUserMail(String userMail);
}
