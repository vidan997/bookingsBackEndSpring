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

    @Column(name = "name")
    private String name;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "capacity")
    private int capacity;

    public RoomEntity() {}

    public RoomEntity(long id, long placeid, String name, String roomType, int capacity) {
        this.id = id;
        this.placeid = placeid;
        this.name = name;
        this.roomType = roomType;
        this.capacity = capacity;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, placeid, name, roomType, capacity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RoomEntity other)) return false;
        return id == other.id
                && placeid == other.placeid
                && capacity == other.capacity
                && Objects.equals(name, other.name)
                && Objects.equals(roomType, other.roomType);
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id=" + id +
                ", placeid=" + placeid +
                ", name='" + name + '\'' +
                ", roomType='" + roomType + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}