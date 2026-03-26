package bookingsproject.app.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "payment_hold")
public class PaymentHoldEntity implements ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "paypal_order_id")
    private String paypalOrderId;

    @Column(name = "place_id")
    private long placeId;

    @Column(name = "room_id")
    private long roomId;

    @Column(name = "user_id")
    private String userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booked_from")
    private Date bookedFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booked_to")
    private Date bookedTo;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires_at")
    private Date expiresAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public PaymentHoldEntity() {
    }

    public PaymentHoldEntity(long id, String paypalOrderId, long placeId, long roomId, String userId,
                             Date bookedFrom, Date bookedTo, String status, Date expiresAt, Date createdAt) {
        this.id = id;
        this.paypalOrderId = paypalOrderId;
        this.placeId = placeId;
        this.roomId = roomId;
        this.userId = userId;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.status = status;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getPaypalOrderId() { return paypalOrderId; }
    public void setPaypalOrderId(String paypalOrderId) { this.paypalOrderId = paypalOrderId; }

    public long getPlaceId() { return placeId; }
    public void setPlaceId(long placeId) { this.placeId = placeId; }

    public long getRoomId() { return roomId; }
    public void setRoomId(long roomId) { this.roomId = roomId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Date getBookedFrom() { return bookedFrom; }
    public void setBookedFrom(Date bookedFrom) { this.bookedFrom = bookedFrom; }

    public Date getBookedTo() { return bookedTo; }
    public void setBookedTo(Date bookedTo) { this.bookedTo = bookedTo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Date expiresAt) { this.expiresAt = expiresAt; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}