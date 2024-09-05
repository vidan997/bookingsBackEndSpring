/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsproject.app.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Munja
 */
public class BookingDto implements ApplicationDto{
    
    private long id;
    @NotNull
    private long placeId;
    @NotNull
    private String userid;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private int guestNumber;
    @NotNull
    private Date bookedFrom;
    @NotNull
    private Date bookedTo;
    @NotNull
    private String placeTitle;
    @NotNull
    private String placeImage;

    public BookingDto(long id, long placeId, String userid, String firstName, String lastName, int guestNumber, Date bookedFrom, Date bookedTo, String placeTitle, String placeImage) {
        this.id = id;
        this.placeId = placeId;
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.guestNumber = guestNumber;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.placeTitle = placeTitle;
        this.placeImage = placeImage;
    }

    public BookingDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setQuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public Date getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(Date bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public Date getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(Date bookedTo) {
        this.bookedTo = bookedTo;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }
    
    
    
    
}
