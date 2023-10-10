package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.DefaultGroup;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.GroupMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.BindingResultErrorsHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;
import ua.foxminded.javaspring.kocherga.web_application.service.RedirectAttributesMessageHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.GroupValidationException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;
    private final RedirectAttributesMessageHandler attrMsgHandler;
    private final BindingResultErrorsHandler bindingResultErrHandler;

    public GroupServiceImpl(GroupMapper groupMapper,
                            GroupRepository groupRepository,
                            RedirectAttributesMessageHandler attrMsgHandler,
                            BindingResultErrorsHandler bindingResultErrHandler) {
        this.groupMapper = groupMapper;
        this.groupRepository = groupRepository;
        this.attrMsgHandler = attrMsgHandler;
        this.bindingResultErrHandler = bindingResultErrHandler;
    }

    @Override
    public List<GroupDto> getAllGroupsForManagement() {
        return getAllGroups()
                .stream()
                .filter(group -> !Objects.equals(group.getId(), DefaultGroup.UNSELECTED.getId()))
                .sorted(Comparator.comparing(GroupDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupDto> getAllGroups() {
        return groupMapper.groupListToGroupDtoList(groupRepository.findAll());
    }

    public List<GroupDto> getAllStudentsGroups() {
        List<String> excludedGroupNames = Arrays.asList("admin", "professor");
        return groupRepository.findByNameNotIn(excludedGroupNames)
                .stream()
                .map(groupMapper::groupToGroupDto)
                .sorted(Comparator.comparingLong(GroupDto::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveNewGroup(GroupDto groupDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateGroupBindingResultErrors(bindingResult);
        checkGroupNameExist(groupDto);
        Group group = new Group();
        group.setName(groupDto.getName());
        groupRepository.save(group);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Group added successfully!");
    }

    private void checkGroupNameExist(GroupDto groupDto) {
        boolean notSameGroupName = true;
        if (!(groupDto.getId() == null)) {
            notSameGroupName = !groupRepository.getGroupById(groupDto.getId()).getName().equals(groupDto.getName());
        }
        if (groupRepository.existsByName(groupDto.getName()) && notSameGroupName) {
            throw new GroupValidationException("Group with the same name already exists.");
        }
    }

    @Transactional
    @Override
    public void updateGroup(GroupDto groupDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateGroupBindingResultErrors(bindingResult);
        checkGroupNameExist(groupDto);
        Group groupToUpdate = groupRepository.getGroupById(groupDto.getId());
        groupToUpdate.setName(groupDto.getName());
        groupRepository.save(groupToUpdate);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Group updated successfully!");
    }

    @Transactional
    @Override
    public void deleteGroup(Long groupId, RedirectAttributes redirectAttributes) {
        if (groupRepository.existsById(groupId)) {
            groupRepository.deleteById(groupId);
            attrMsgHandler.setSuccessMessage(redirectAttributes, "Group deleted successfully!");
        } else {
            attrMsgHandler.setErrorMessage(redirectAttributes, "Course not found or could not be deleted");
        }
    }
}
