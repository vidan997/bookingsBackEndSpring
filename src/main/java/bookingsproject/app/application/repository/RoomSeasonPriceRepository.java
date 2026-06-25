package bookingsproject.app.application.repository;

import bookingsproject.app.application.model.RoomSeasonPriceEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomSeasonPriceRepository extends JpaRepository<RoomSeasonPriceEntity, Long> {

    List<RoomSeasonPriceEntity> findByRoomId(long roomId);

    List<RoomSeasonPriceEntity> findAllByRoomIdAndDateFromLessThanEqualAndDateToGreaterThanEqual(
            long roomId,
            LocalDate date,
            LocalDate date2
    );
}