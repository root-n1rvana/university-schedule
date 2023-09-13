package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDto groupToGroupDto(Group group);

    Group groupDtoToGroup(GroupDto groupDto);

    List<GroupDto> groupListToGroupDtoList(List<Group> groups);
}
