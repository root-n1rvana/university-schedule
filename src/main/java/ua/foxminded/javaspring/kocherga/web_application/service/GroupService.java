package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group getGroupById(long groupId) {
        return groupRepository.getGroupById(groupId);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
