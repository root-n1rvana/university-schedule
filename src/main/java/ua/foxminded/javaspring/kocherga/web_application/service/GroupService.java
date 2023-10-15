package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAllGroups();

    List<GroupDto> getAllGroupsForManagement();

    @Transactional
    void saveNewGroup(GroupDto groupDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void updateGroup(GroupDto groupDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void deleteGroup(Long groupId, RedirectAttributes redirectAttributes);

    List<GroupDto> getAllStudentsGroups();
}
