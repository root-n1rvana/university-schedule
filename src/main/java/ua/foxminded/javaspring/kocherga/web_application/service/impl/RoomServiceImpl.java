package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Room;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RoomDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.RoomMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoomRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.RoomService;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<RoomDto> getAllRoomsDto() {
        return roomMapper.roomListToRoomDtoList(roomRepository.findAll());
    }
}
