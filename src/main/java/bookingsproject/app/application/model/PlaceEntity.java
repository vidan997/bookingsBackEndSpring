package bookingsproject.app.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "place")
public class PlaceEntity implements ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Lob
    @Column(name = "image_urls_json")
    private String imageUrlsJson;

    @Column(name = "avaiable_from")
    private Date avaiableFrom;

    @Column(name = "avaiable_to")
    private Date avaiableTo;

    @Column(name = "userid")
    private String userid;

    public PlaceEntity() {}

    public PlaceEntity(long id, String title, String description, String imageUrl, String imageUrlsJson,
                       Date avaiableFrom, Date avaiableTo, String userid) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageUrlsJson = imageUrlsJson;
        this.avaiableFrom = avaiableFrom;
        this.avaiableTo = avaiableTo;
        this.userid = userid;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getImageUrlsJson() { return imageUrlsJson; }
    public void setImageUrlsJson(String imageUrlsJson) { this.imageUrlsJson = imageUrlsJson; }

    public Date getAvaiableFrom() { return avaiableFrom; }
    public void setAvaiableFrom(Date avaiableFrom) { this.avaiableFrom = avaiableFrom; }

    public Date getAvaiableTo() { return avaiableTo; }
    public void setAvaiableTo(Date avaiableTo) { this.avaiableTo = avaiableTo; }

    public String getUserId() { return userid; }
    public void setUserId(String userid) { this.userid = userid; }

    @Override
    public int hashCode() {
        return 5;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final PlaceEntity other = (PlaceEntity) obj;
        if (this.id != other.id) return false;
        if (!Objects.equals(this.title, other.title)) return false;
        if (!Objects.equals(this.description, other.description)) return false;
        if (!Objects.equals(this.imageUrl, other.imageUrl)) return false;
        if (!Objects.equals(this.imageUrlsJson, other.imageUrlsJson)) return false;
        if (!Objects.equals(this.userid, other.userid)) return false;
        if (!Objects.equals(this.avaiableFrom, other.avaiableFrom)) return false;
        return Objects.equals(this.avaiableTo, other.avaiableTo);
    }

    @Override
    public String toString() {
        return "PlaceEntity{" + "id=" + id + ", title=" + title + ", description=" + description
                + ", imageUrl=" + imageUrl + ", imageUrlsJson=" + imageUrlsJson
                + ", avaiableFrom=" + avaiableFrom + ", avaiableTo=" + avaiableTo
                + ", userMail=" + userid + '}';
    }
}

