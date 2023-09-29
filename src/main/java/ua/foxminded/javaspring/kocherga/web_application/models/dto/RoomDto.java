package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;

import java.util.Set;

public class RoomDto {

    private Long id;
    private String roomLabel;
    private String roomDescription;
    private Set<Lesson> lessonsList;

    public RoomDto() {
    }

    public RoomDto(Long id, String roomLabel, String roomDescription, Set<Lesson> lessonsList) {
        this.id = id;
        this.roomLabel = roomLabel;
        this.roomDescription = roomDescription;
        this.lessonsList = lessonsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Lesson> getLessonsList() {
        return lessonsList;
    }

    public void setLessonsList(Set<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }
}
