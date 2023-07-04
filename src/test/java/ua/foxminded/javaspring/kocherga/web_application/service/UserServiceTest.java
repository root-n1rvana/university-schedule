package ua.foxminded.javaspring.kocherga.web_application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void saveAndRetrieveUser() {
        //given
        String userFirstName = "TestUser";
        String userLastName = "TestLastName";

        long groupId = 3;
        Group testGroup = groupRepository.getGroupById(groupId);

        long roleId = 1;
        Role testRole = roleRepository.getRoleById(roleId);

        List<Course> testCourses = new ArrayList<>();
        //Numbers 1-5 are random ID's
        testCourses.add(courseRepository.getCourseById(1L));
        testCourses.add(courseRepository.getCourseById(2L));
        testCourses.add(courseRepository.getCourseById(3L));
        testCourses.add(courseRepository.getCourseById(4L));
        testCourses.add(courseRepository.getCourseById(5L));

        // Create a test user
        User testUser = new User();
        testUser.setUserName(userFirstName);
        testUser.setUserLastname(userLastName);
        testUser.setOwnerGroup(testGroup);
        testUser.setOwnerRole(testRole);
        testUser.setUserCourses(testCourses);

        // Save user using the repository
        User savedUser = userRepository.save(testUser);

        // Retrieve user from the database
        User retrievedUser = userService.getUserByUserId(savedUser.getId());

        // Assert against the created user
        Assertions.assertThat(savedUser.getId()).isEqualTo(retrievedUser.getId());
        Assertions.assertThat(savedUser.getUserName()).isEqualTo(retrievedUser.getUserName());
        Assertions.assertThat(savedUser.getUserLastname()).isEqualTo(retrievedUser.getUserLastname());
        Assertions.assertThat(savedUser.getOwnerGroup()).isEqualTo(retrievedUser.getOwnerGroup());
        Assertions.assertThat(savedUser.getOwnerRole()).isEqualTo(retrievedUser.getOwnerRole());
        Assertions.assertThat(savedUser.getUserCourses()).isEqualTo(retrievedUser.getUserCourses());
    }

    @Test
    void getUsersByGroupId_ShouldReturnListOfUsers() {

        int groupId = 1;

        List<User> users = userService.getUsersByGroupId(groupId);

        assertEquals(3, users.size());
        assertEquals("admin", users.get(0).getUserName());
        assertEquals("alex", users.get(1).getUserName());
        assertEquals("John", users.get(2).getUserName());
    }


}
