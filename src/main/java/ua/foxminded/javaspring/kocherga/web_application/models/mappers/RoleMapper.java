package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RoleDto;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto roleToRoleDto(Role role);

    Role roleDtoToRole(RoleDto roleDto);

    List<RoleDto> roleListToRoleDtoList(List<Role> roles);

    List<Role> roleDtoListToRoleList(List<RoleDto> roles);

    Set<RoleDto> roleSetToRoleDtoSet(Set<Role> roles);

    Set<Role> roleDtoSetToRoleSet(Set<RoleDto> roles);
}
