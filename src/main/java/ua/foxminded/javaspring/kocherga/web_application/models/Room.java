package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "label", length = 10, unique = true, nullable = false)
    private String roomLabel;

    @Column(name = "description", length = 100)
    private String roomDescription;

    public Room() {
    }

    public Room(String roomLabel, String roomDescription) {
        this.roomLabel = roomLabel;
        this.roomDescription = roomDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomLabel() {
        return roomLabel;
    }

    public void setRoomLabel(String roomLabel) {
        this.roomLabel = roomLabel;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomLabel.equals(room.roomLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomLabel);
    }
}
