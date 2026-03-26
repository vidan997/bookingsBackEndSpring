package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class PlaceDto implements ApplicationDto {

    @NotNull
    private long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String imageUrl;
    private List<String> imageUrls;
    @NotNull
    private Date avaiableFrom;
    @NotNull
    private Date avaiableTo;
    @NotNull
    private String userId;

    private List<RoomDto> rooms;

    public PlaceDto() {}

    public PlaceDto(long id, String title, String description,
                    String imageUrl, List<String> imageUrls,
                    Date avaiableFrom, Date avaiableTo,
                    String userid, List<RoomDto> rooms) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageUrls = imageUrls;
        this.avaiableFrom = avaiableFrom;
        this.avaiableTo = avaiableTo;
        this.userId = userid;
        this.rooms = rooms;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }

    public Date getAvaiableFrom() { return avaiableFrom; }
    public void setAvaiableFrom(Date avaiableFrom) { this.avaiableFrom = avaiableFrom; }

    public Date getAvaiableTo() { return avaiableTo; }
    public void setAvaiableTo(Date avaiableTo) { this.avaiableTo = avaiableTo; }

    public String getUserId() { return userId; }
    public void setUserId(String userid) { this.userId = userid; }

    public List<RoomDto> getRooms() { return rooms; }
    public void setRooms(List<RoomDto> rooms) { this.rooms = rooms; }
}