/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.repository;

import bookingsproject.app.application.model.UserEntity;
import jakarta.annotation.Nullable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Munja
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    @Nullable
    Optional<UserEntity> findByEmail(String email);
    
}
