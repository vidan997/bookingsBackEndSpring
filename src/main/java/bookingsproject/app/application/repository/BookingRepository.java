package bookingsproject.app.application.repository;

import bookingsproject.app.application.model.BookingEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findByUserid(String userMail);

    @Query("SELECT COUNT(b) > 0 FROM BookingEntity b "
            + "WHERE b.roomid = :roomid "
            + "AND b.bookedFrom < :bookedTo "
            + "AND b.bookedTo > :bookedFrom")
    boolean existsOverlappingForRoom(@Param("roomid") long roomid,
            @Param("bookedFrom") Date bookedFrom,
            @Param("bookedTo") Date bookedTo);

    @Query("SELECT COUNT(b) FROM BookingEntity b "
            + "WHERE b.roomid = :roomid "
            + "AND b.bookedFrom < :bookedTo "
            + "AND b.bookedTo > :bookedFrom")
    long countOverlappingBookingsForRoom(@Param("roomid") long roomid,
            @Param("bookedFrom") Date bookedFrom,
            @Param("bookedTo") Date bookedTo);

    List<BookingEntity> findByPlaceId(long placeId);

}
