package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_label")
    private String roomLabel;

    @Column(name = "room_description")
    private String roomDescription;

    @OneToMany(mappedBy = "ownerRoom")
    private List<Lesson> lessonsList;

    public Room() {
    }

    public Room(int id, String roomLabel, String roomDescription) {
        this.id = id;
        this.roomLabel = roomLabel;
        this.roomDescription = roomDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Lesson> getLessonsList() {
        return lessonsList;
    }

    public void setLessonsList(List<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && Objects.equals(roomLabel, room.roomLabel) && Objects.equals(roomDescription, room.roomDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomLabel, roomDescription);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomLabel='" + roomLabel + '\'' +
                ", roomDescription='" + roomDescription + '\'' +
                '}';
    }
}
