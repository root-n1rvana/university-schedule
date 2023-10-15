package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<UserDto> userListToUserDtoList(List<User> users);

    default Page<UserDto> pageUserToPageUserDto(Page<User> page) {
        return page.map(this::userToUserDto);
    }
}
