/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author Munja
 */
public class PlaceDto implements ApplicationDto{
    
    @NotNull
    private long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String imageUrl;
    @NotNull
    private int price;
    @NotNull
    private Date avaiableFrom;
    @NotNull
    private Date avaiableTo;
    @NotNull
    private String userMail;

    public PlaceDto() {
    }
    
    public PlaceDto(long id, String title, String description, String imageUrl, int price, Date avaiableFrom, Date avaiableTo, String userMail) {
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
    
    
    
}
