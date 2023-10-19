package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "user.roles", qualifiedByName = "roleToRoleName")
    UserDto userToUserDto(User user);

    @Named("roleToRoleName")
    static Set<RoleName> roleToRoleName(Set<Role> roles) {
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
    }

    User userDtoToUser(UserDto userDto);

    List<UserDto> userListToUserDtoList(List<User> users);

    default Page<UserDto> pageUserToPageUserDto(Page<User> page) {
        return page.map(this::userToUserDto);
    }
}
