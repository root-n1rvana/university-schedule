package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import java.util.Set;

public class RoomDto {

    private Long id;
    private String roomLabel;
    private String roomDescription;
//    private Set<LessonDto> lessonsList;

    public RoomDto() {
    }

    public RoomDto(Long id, String roomLabel, String roomDescription/*, Set<LessonDto> lessonsList*/) {
        this.id = id;
        this.roomLabel = roomLabel;
        this.roomDescription = roomDescription;
//        this.lessonsList = lessonsList;
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

//    public Set<LessonDto> getLessonsList() {
//        return lessonsList;
//    }
//
//    public void setLessonsList(Set<LessonDto> lessonsList) {
//        this.lessonsList = lessonsList;
//    }
}
