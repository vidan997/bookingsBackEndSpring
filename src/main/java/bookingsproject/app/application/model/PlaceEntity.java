/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Munja
 */
@Entity
@Table(name = "place")
public class PlaceEntity  implements ApplicationEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    private int price;
    @Column(name = "avaiable_from")
    private Date avaiableFrom;
    @Column(name = "avaiable_to")
    private Date avaiableTo;
    @Column(name = "user_mail")
    private String userMail;

    public PlaceEntity() {
    }

    public PlaceEntity(long id, String title, String description, String imageUrl, int price, Date avaiableFrom, Date avaiableTo, String userMail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.avaiableFrom = avaiableFrom;
        this.avaiableTo = avaiableTo;
        this.userMail = userMail;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getAvaiableFrom() {
        return avaiableFrom;
    }

    public void setAvaiableFrom(Date avaiableFrom) {
        this.avaiableFrom = avaiableFrom;
    }

    public Date getAvaiableTo() {
        return avaiableTo;
    }

    public void setAvaiableTo(Date avaiableTo) {
        this.avaiableTo = avaiableTo;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlaceEntity other = (PlaceEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        if (!Objects.equals(this.userMail, other.userMail)) {
            return false;
        }
        if (!Objects.equals(this.avaiableFrom, other.avaiableFrom)) {
            return false;
        }
        return Objects.equals(this.avaiableTo, other.avaiableTo);
    }

    @Override
    public String toString() {
        return "PlaceEntity{" + "id=" + id + ", title=" + title + ", description=" + description + ", imageUrl=" + imageUrl + ", price=" + price + ", avaiableFrom=" + avaiableFrom + ", avaiableTo=" + avaiableTo + ", userMail=" + userMail + '}';
    }
    
    
    
}
