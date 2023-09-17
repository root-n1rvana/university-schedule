package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.GroupMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;

import java.util.Comparator;
import java.util.List;
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

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public List<GroupDto> getAllGroupsForManagement() {
        return groupMapper.groupListToGroupDtoList(groupRepository.findAll())
                .stream()
                .filter(group -> group.getId() != 9L) //excluding default empty Group for new users
                .sorted(Comparator.comparing(GroupDto::getId))
                .collect(Collectors.toList());
    }

    public List<GroupDto> getAllGroupsForStudents() {
        return groupMapper.groupListToGroupDtoList(groupRepository.findAll())
                .stream()
                .filter(group -> group.getId() != 1L) //excluding admin Group for new Students
                .filter(group -> group.getId() != 2L) //excluding professor Group for new Students
                .filter(group -> group.getId() != 9L) //excluding empty default Group
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
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void save(GroupDto groupDto) {
        Group group = groupMapper.groupDtoToGroup(groupDto);
        groupRepository.save(group);
    }

    @Transactional
    public RedirectAttributesDto saveWithRedirAttr(String newGroupName) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto(newGroupName);
        if (groupRepository.existsByName(newGroupName)) {
            redirectAttributesDto.setName("errorMessage");
            redirectAttributesDto.setValue("Group with the same name already exists.");
        } else {
            Group group = new Group();
            group.setName(newGroupName);
            save(group);
            redirectAttributesDto.setName("successMessage");
            redirectAttributesDto.setValue("Group added successfully!");
        }
        return redirectAttributesDto;
    }

    @Transactional
    public void deleteGroupByName(String groupName) {
        groupRepository.deleteByName(groupName);
    }

    @Transactional
    public RedirectAttributesDto deleteWithRedirAttr(Long groupId) {
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
