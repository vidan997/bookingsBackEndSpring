package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class RoomDto implements ApplicationDto {

    @NotNull
    private long id;

    @NotNull
    private long placeid;

    @NotNull
    private String name;

    @NotNull
    private String roomType;

    @NotNull
    private int capacity;

    private List<RoomSeasonPriceDto> seasonPrices;

    public RoomDto() {}

    public RoomDto(long id, long placeid, String name, String roomType, int capacity,
                   List<RoomSeasonPriceDto> seasonPrices) {
        this.id = id;
        this.placeid = placeid;
        this.name = name;
        this.roomType = roomType;
        this.capacity = capacity;
        this.seasonPrices = seasonPrices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlaceid() {
        return placeid;
    }

    public void setPlaceid(long placeid) {
        this.placeid = placeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<RoomSeasonPriceDto> getSeasonPrices() {
        return seasonPrices;
    }

    public void setSeasonPrices(List<RoomSeasonPriceDto> seasonPrices) {
        this.seasonPrices = seasonPrices;
    }
}