package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.Room;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RoomDto;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomDto roomToRoomDto(Room room);

    Room roomDtoToRoom(RoomDto roomDto);

    List<RoomDto> roomListToRoomDtoList(List<Room> rooms);

    List<Room> roomDtoListToRoomList(List<RoomDto> rooms);

    Set<RoomDto> roomSetToRoomDtoSet(Set<Room> rooms);

    Set<Room> roomDtoSetToRoomSet(Set<RoomDto> rooms);
}
