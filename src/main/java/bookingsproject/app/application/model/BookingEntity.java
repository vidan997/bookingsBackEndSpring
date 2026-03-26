package bookingsproject.app.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "booking")
public class BookingEntity implements ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "placeid")
    private long placeId;

    @Column(name = "roomid")
    private long roomid;

    @Column(name = "userid")
    private String userid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "booked_from")
    private Date bookedFrom;

    @Column(name = "booked_to")
    private Date bookedTo;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "price_at_booking")
    private double priceAtBooking;

    @Column(name = "place_title")
    private String placeTitle;

    @Column(name = "place_image")
    private String placeImage;

    @Column(name = "owner_phone")
    private String ownerPhone;

    public BookingEntity() {
    }

    public BookingEntity(long id, long placeId, long roomid, String userid, String firstName, String lastName,
                         Date bookedFrom, Date bookedTo, String roomType, double priceAtBooking,
                         String placeTitle, String placeImage, String ownerPhone) {
        this.id = id;
        this.placeId = placeId;
        this.roomid = roomid;
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.roomType = roomType;
        this.priceAtBooking = priceAtBooking;
        this.placeTitle = placeTitle;
        this.placeImage = placeImage;
        this.ownerPhone = ownerPhone;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getPlaceId() { return placeId; }
    public void setPlaceId(long placeId) { this.placeId = placeId; }

    public long getRoomid() { return roomid; }
    public void setRoomid(long roomid) { this.roomid = roomid; }

    public String getUserId() { return userid; }
    public void setUserId(String userid) { this.userid = userid; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getBookedFrom() { return bookedFrom; }
    public void setBookedFrom(Date bookedFrom) { this.bookedFrom = bookedFrom; }

    public Date getBookedTo() { return bookedTo; }
    public void setBookedTo(Date bookedTo) { this.bookedTo = bookedTo; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public double getPriceAtBooking() { return priceAtBooking; }
    public void setPriceAtBooking(double priceAtBooking) { this.priceAtBooking = priceAtBooking; }

    public String getPlaceTitle() { return placeTitle; }
    public void setPlaceTitle(String placeTitle) { this.placeTitle = placeTitle; }

    public String getPlaceImage() { return placeImage; }
    public void setPlaceImage(String placeImage) { this.placeImage = placeImage; }

    public String getOwnerPhone() { return ownerPhone; }
    public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; }

    @Override
    public int hashCode() { return 3; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final BookingEntity other = (BookingEntity) obj;
        if (this.id != other.id) return false;
        if (this.placeId != other.placeId) return false;
        if (this.roomid != other.roomid) return false;
        if (Double.doubleToLongBits(this.priceAtBooking) != Double.doubleToLongBits(other.priceAtBooking)) return false;
        if (!Objects.equals(this.userid, other.userid)) return false;
        if (!Objects.equals(this.firstName, other.firstName)) return false;
        if (!Objects.equals(this.lastName, other.lastName)) return false;
        if (!Objects.equals(this.bookedFrom, other.bookedFrom)) return false;
        if (!Objects.equals(this.bookedTo, other.bookedTo)) return false;
        if (!Objects.equals(this.roomType, other.roomType)) return false;
        if (!Objects.equals(this.placeTitle, other.placeTitle)) return false;
        if (!Objects.equals(this.placeImage, other.placeImage)) return false;
        return Objects.equals(this.ownerPhone, other.ownerPhone);
    }

    @Override
    public String toString() {
        return "BookingEntity{" + "id=" + id + ", placeId=" + placeId + ", roomid=" + roomid
                + ", userMail=" + userid + ", firstName=" + firstName + ", lastName=" + lastName
                + ", bookedFrom=" + bookedFrom + ", bookedTo=" + bookedTo
                + ", roomType=" + roomType + ", priceAtBooking=" + priceAtBooking
                + ", ownerPhone=" + ownerPhone + '}';
    }
}