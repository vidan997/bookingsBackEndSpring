package bookingsproject.app.application.repository;

import bookingsproject.app.application.model.RoomEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    List<RoomEntity> findByPlaceid(long placeid);
}
