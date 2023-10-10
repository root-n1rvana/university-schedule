package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room getRoomById(long roomId);
}
