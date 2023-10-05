package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;

import java.util.List;

public interface GroupService {

    void save(GroupDto groupDto);

    Group getGroupById(long groupId);

    List<GroupDto> getAllGroups();

    boolean existByGroupId(Long groupId);

    RedirectAttributesDto saveAndGetRedirAttr(String newGroupName);

    RedirectAttributesDto updateAndGetRedirAttr(GroupDto groupDto);
    RedirectAttributesDto deleteAndGetRedirAttr(Long groupId);
}
