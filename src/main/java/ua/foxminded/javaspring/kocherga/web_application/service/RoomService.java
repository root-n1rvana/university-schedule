package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.dto.RoomDto;

import java.util.List;

public interface RoomService {

    List<RoomDto> getAllRooms();

    List<RoomDto> getAllRoomsDto();
}
