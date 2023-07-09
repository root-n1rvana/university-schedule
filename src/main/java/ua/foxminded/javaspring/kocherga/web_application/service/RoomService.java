package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Room;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room getRoomById(long roomId) {
        return roomRepository.getRoomById(roomId);
    }

    public Room getRoomByLabel(String roomLabel) {
        return roomRepository.getRoomByRoomLabel(roomLabel);
    }
}