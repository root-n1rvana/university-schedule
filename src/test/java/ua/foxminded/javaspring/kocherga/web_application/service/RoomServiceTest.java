package ua.foxminded.javaspring.kocherga.web_application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.kocherga.web_application.models.Room;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoomRepository;

@SpringBootTest
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    void saveAndRetrieveRoom() {
        //given
        String roomLabel = "Test Room";
        String roomDescription = "This is a test room";

        // Create a test room
        Room testRoom = new Room();
        testRoom.setRoomLabel(roomLabel);
        testRoom.setRoomDescription(roomDescription);

        // Save the room using the repository
        Room savedRoom = roomRepository.save(testRoom);

        // Retrieve the room from the database
        Room retrievedRoom = roomService.getRoomByLabel(roomLabel);

        // Assert against the created room
        Assertions.assertThat(savedRoom.getId()).isEqualTo(retrievedRoom.getId());
        Assertions.assertThat(savedRoom.getRoomLabel()).isEqualTo(retrievedRoom.getRoomLabel());
        Assertions.assertThat(savedRoom.getRoomDescription()).isEqualTo(retrievedRoom.getRoomDescription());
    }
}
