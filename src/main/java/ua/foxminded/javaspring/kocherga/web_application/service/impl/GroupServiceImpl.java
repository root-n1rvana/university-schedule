package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.DefaultGroup;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.GroupMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public Group getGroupById(long groupId) {
        return groupRepository.getGroupById(groupId);
    }

    public GroupDto getGroupDtoById(long groupId) {
        return groupMapper.groupToGroupDto(getGroupById(groupId));
    }

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

    @Override
    public boolean existByGroupId(Long groupId) {
        return groupRepository.existsById(groupId);
    }

    @Transactional
    @Override
    public void save(GroupDto groupDto) {
        Group group = groupMapper.groupDtoToGroup(groupDto);
        groupRepository.save(group);
    }

    @Transactional
    @Override
    public RedirectAttributesDto saveAndGetRedirAttr(String newGroupName) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (groupRepository.existsByName(newGroupName)) {
            redirectAttributesDto.setName("errorMessage");
            redirectAttributesDto.setValue("Group with the same name already exists.");
        } else {
            Group group = new Group();
            group.setName(newGroupName);
            groupRepository.save(group);
            redirectAttributesDto.setName("successMessage");
            redirectAttributesDto.setValue("Group added successfully!");
        }
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto updateAndGetRedirAttr(GroupDto groupDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (groupRepository.existsByName(groupDto.getName())) {
            redirectAttributesDto.setName("errorMessage");
            redirectAttributesDto.setValue("Group with the same name already exists.");
        } else {
            Group groupToUpdate = getGroupById(groupDto.getId());
            groupToUpdate.setName(groupDto.getName());
            groupRepository.save(groupToUpdate);
            redirectAttributesDto.setName("successMessage");
            redirectAttributesDto.setValue("Group updated successfully!");
        }
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto deleteAndGetRedirAttr(Long groupId) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (groupRepository.existsById(groupId)) {
            groupRepository.deleteById(groupId);
            redirectAttributesDto.setName("deletionSucceeded");
            redirectAttributesDto.setValue("Group deleted successfully!");
        } else {
            redirectAttributesDto.setName("deletionError");
            redirectAttributesDto.setValue("Course not found or could not be deleted");
        }
        return redirectAttributesDto;
    }
}
