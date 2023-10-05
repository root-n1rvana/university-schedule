package ua.foxminded.javaspring.kocherga.web_application.models.dto;

public class RoomDto {

    private Long id;
    private String roomLabel;
    private String roomDescription;

    public RoomDto() {
    }

    public RoomDto(Long id, String roomLabel, String roomDescription) {
        this.id = id;
        this.roomLabel = roomLabel;
        this.roomDescription = roomDescription;
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
}
