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
@Table(name = "booking")
public class BookingEntity  implements ApplicationEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "placeid")
    private long placeId;
    @Column(name = "usermail")
    private String userMail;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "guest_number")
    private int guestNumber;
    @Column(name = "booked_from")
    private Date bookedFrom;
    @Column(name = "booked_to")
    private Date bookedTo;
    @Column(name = "place_title")
    private String placeTitle;
    @Column(name = "place_image")
    private String placeImage;

    public BookingEntity() {
    }
   
    public BookingEntity(long id, long placeId, String userMail, String firstName, String lastName, int guestNumber, Date bookedFrom, Date bookedTo, String placeTitle, String placeImage) {
        this.id = id;
        this.placeId = placeId;
        this.userMail = userMail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.guestNumber = guestNumber;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.placeTitle = placeTitle;
        this.placeImage = placeImage;
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

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
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

    public void setGuestNumber(int guestNumber) {
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

    @Override
    public int hashCode() {
        int hash = 3;
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
        final BookingEntity other = (BookingEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.placeId != other.placeId) {
            return false;
        }
        if (this.guestNumber != other.guestNumber) {
            return false;
        }
        if (!Objects.equals(this.userMail, other.userMail)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.bookedFrom, other.bookedFrom)) {
            return false;
        }
        return Objects.equals(this.bookedTo, other.bookedTo);
    }

    @Override
    public String toString() {
        return "BookingEntity{" + "id=" + id + ", placeId=" + placeId + ", userMail=" + userMail + ", firstName=" + firstName + ", lastName=" + lastName + ", questNumber=" + guestNumber + ", bookedFrom=" + bookedFrom + ", bookedTo=" + bookedTo + '}';
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
