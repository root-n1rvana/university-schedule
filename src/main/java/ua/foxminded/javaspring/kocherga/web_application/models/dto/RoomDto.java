package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RoomDto {

    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10, message = "Room label should have at least 2 and max 10 characters")
    private String roomLabel;

    @Size(max = 100, message = "Room label should have max 100 characters")
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
