package bookingsproject.app.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "room")
public class RoomEntity implements ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "placeid")
    private long placeid;

    @Column(name = "room_type")
    private String roomType;

    private double price;

    private int quantity;

    public RoomEntity() {}

    public RoomEntity(long id, long placeid, String roomType, double price, int quantity) {
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

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final RoomEntity other = (RoomEntity) obj;
        if (this.id != other.id) return false;
        if (this.placeid != other.placeid) return false;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) return false;
        if (this.quantity != other.quantity) return false;
        return Objects.equals(this.roomType, other.roomType);
    }

    @Override
    public String toString() {
        return "RoomEntity{" + "id=" + id + ", placeid=" + placeid + ", roomType=" + roomType
                + ", price=" + price + ", quantity=" + quantity + '}';
    }
}