package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.DefaultGroup;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.GroupMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    public Group getGroupById(long groupId) {
        return groupRepository.getGroupById(groupId);
    }

    public GroupDto getGroupDtoById(long groupId) {
        return groupMapper.groupToGroupDto(groupRepository.getGroupById(groupId));
    }

    public List<GroupDto> getAllGroups() {
        return groupMapper.groupListToGroupDtoList(groupRepository.findAll());
    }

    public List<GroupDto> getAllGroupsForManagement() {
        return groupMapper.groupListToGroupDtoList(groupRepository.findAll())
                .stream()
                .filter(group -> !Objects.equals(group.getId(), DefaultGroup.UNSELECTED.getId()))
                .sorted(Comparator.comparing(GroupDto::getId))
                .collect(Collectors.toList());
    }

    private boolean adminAndProfessorGroupFilter(GroupDto group) {
        return !Objects.equals(group.getId(), DefaultGroup.ADMIN.getId()) &&
                !Objects.equals(group.getId(), DefaultGroup.PROFESSOR.getId());
    }

    public List<GroupDto> getAllGroupsForStudents() {
        return groupMapper.groupListToGroupDtoList(groupRepository.findAll())
                .stream()
                .filter(this::adminAndProfessorGroupFilter)
                .sorted(Comparator.comparing(GroupDto::getId))
                .collect(Collectors.toList());
    }

    public boolean existByGroupName(String groupName) {
        return groupRepository.existsByName(groupName);
    }

    public boolean existByGroupId(Long groupId) {
        return groupRepository.existsById(groupId);
    }

    @Transactional
    public void save(GroupDto groupDto) {
        Group group = groupMapper.groupDtoToGroup(groupDto);
        groupRepository.save(group);
    }

    @Transactional
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
