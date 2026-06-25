package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RoomSeasonPriceDto implements ApplicationDto {

    @NotNull
    private long id;

    @NotNull
    private long roomId;

    @NotNull
    private String seasonName;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @NotNull
    private BigDecimal pricePerNight;

    public RoomSeasonPriceDto() {}

    public RoomSeasonPriceDto(long id, long roomId, String seasonName,
                              LocalDate dateFrom, LocalDate dateTo,
                              BigDecimal pricePerNight) {
        this.id = id;
        this.roomId = roomId;
        this.seasonName = seasonName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.pricePerNight = pricePerNight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}