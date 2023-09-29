package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.Room;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RoomDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomDto roomToRoomDto(Room room);

    Room roomDtoToRoom(RoomDto roomDto);

    List<RoomDto> roomlistToRoomDtoList(List<Room> rooms);
}
