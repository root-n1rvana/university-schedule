package ua.foxminded.javaspring.kocherga.web_application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.assertj.core.api.Assertions;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;

@SpringBootTest
class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void saveAndRetrieveGroup() {
        //given
        String groupName = "TestGroup";

        Group testGroup = new Group();
        testGroup.setName(groupName);

        Group savedGroup = groupRepository.save(testGroup);

        Group retrievedGroup = groupService.getGroupById(savedGroup.getId());

        Assertions.assertThat(savedGroup.getId()).isEqualTo(retrievedGroup.getId());
        Assertions.assertThat(savedGroup.getName()).isEqualTo(retrievedGroup.getName());
    }
}