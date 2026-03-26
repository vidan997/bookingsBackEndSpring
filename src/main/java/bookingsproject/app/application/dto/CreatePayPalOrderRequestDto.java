package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class CreatePayPalOrderRequestDto {

    @NotNull
    private long placeId;

    @NotNull
    private long roomId;

    @NotNull
    private String userId;

    @NotNull
    private Date bookedFrom;

    @NotNull
    private Date bookedTo;

    public CreatePayPalOrderRequestDto() {
    }

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
}