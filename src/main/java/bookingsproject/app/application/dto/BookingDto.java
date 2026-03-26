package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class BookingDto implements ApplicationDto {

    private long id;

    @NotNull
    private long placeId;

    @NotNull
    private long roomid;

    @NotNull
    private String userid;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;


    @NotNull
    private Date bookedFrom;

    @NotNull
    private Date bookedTo;

    @NotNull
    private String roomType;

    @NotNull
    private double priceAtBooking;

    @NotNull
    private String placeTitle;

    @NotNull
    private String placeImage;

    private String ownerPhone;

    public BookingDto() {
    }

    public BookingDto(long id, long placeId, long roomid, String userid, String firstName, String lastName,
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

    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }

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
}
