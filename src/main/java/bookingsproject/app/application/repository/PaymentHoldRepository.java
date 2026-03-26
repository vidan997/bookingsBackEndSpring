package bookingsproject.app.application.repository;

import bookingsproject.app.application.model.PaymentHoldEntity;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentHoldRepository extends JpaRepository<PaymentHoldEntity, Long> {

    Optional<PaymentHoldEntity> findByPaypalOrderId(String paypalOrderId);

    @Query("SELECT COUNT(h) FROM PaymentHoldEntity h "
            + "WHERE h.roomId = :roomId "
            + "AND h.status = 'CREATED' "
            + "AND h.expiresAt > :now "
            + "AND h.bookedFrom < :bookedTo "
            + "AND h.bookedTo > :bookedFrom")
    long countActiveOverlappingHolds(@Param("roomId") long roomId,
            @Param("bookedFrom") Date bookedFrom,
            @Param("bookedTo") Date bookedTo,
            @Param("now") Date now);
}
