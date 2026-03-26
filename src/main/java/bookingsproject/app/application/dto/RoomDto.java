package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;

public class RoomDto implements ApplicationDto {

    @NotNull
    private long id;

    @NotNull
    private long placeid;

    @NotNull
    private String roomType;

    @NotNull
    private double price;

    @NotNull
    private int quantity;

    public RoomDto() {}

    public RoomDto(long id, long placeid, String roomType, double price, int quantity) {
        this.id = id;
        this.placeid = placeid;
        this.roomType = roomType;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getPlaceid() { return placeid; }
    public void setPlaceid(long placeid) { this.placeid = placeid; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}