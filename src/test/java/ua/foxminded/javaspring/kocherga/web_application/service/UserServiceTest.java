package ua.foxminded.javaspring.kocherga.web_application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

//    private final UserService userService;
//    private final UserRepository userRepository;
//    private final GroupRepository groupRepository;
//    private final CourseRepository courseRepository;
//    private final RoleRepository roleRepository;
//
//    @Autowired
//    public UserServiceTest(UserService userService, UserRepository userRepository, GroupRepository groupRepository,
//                           CourseRepository courseRepository, RoleRepository roleRepository) {
//        this.userService = userService;
//        this.userRepository = userRepository;
//        this.groupRepository = groupRepository;
//        this.courseRepository = courseRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    @Test
//    void saveAndRetrieveUser() {
//        //given
//        String userFirstName = "TestUser";
//        String userLastName = "TestLastName";
//
//        long groupId = 3;
//        Group testGroup = groupRepository.getGroupById(groupId);
//
//        long roleId = 1;
//        Role testRole1 = roleRepository.getRoleById(roleId);
//        Set<Role> testRole = new HashSet<>();
//
//        Set<Course> testCourses = new HashSet<>();
//        //Numbers 1-5 are random ID's
//        testCourses.add(courseRepository.getCourseById(1L));
//        testCourses.add(courseRepository.getCourseById(2L));
//        testCourses.add(courseRepository.getCourseById(3L));
//        testCourses.add(courseRepository.getCourseById(4L));
//        testCourses.add(courseRepository.getCourseById(5L));
//
//        // Create a test user
//        User testUser = new User();
//        testUser.setFirstname(userFirstName);
//        testUser.setLastname(userLastName);
//        testUser.setOwnerGroup(testGroup);
//        testUser.setRoles(testRole);
//        testUser.setUserCourses(testCourses);
//        testUser.setLogin("test999");
//        testUser.setPassword("$2a$10$nyoZIQ7OA7N69DryA41JiuSVi.UD5bqxPRP9fimqoCxjI0cenxmIG");
//
//        // Save user using the repository
//        User savedUser = userRepository.save(testUser);
//
//        // Retrieve user from the database
//        User retrievedUser = userService.getUserByUserId(savedUser.getId());
//
//        // Assert against the created user
//        Assertions.assertThat(savedUser.getId()).isEqualTo(retrievedUser.getId());
//        Assertions.assertThat(savedUser.getFirstname()).isEqualTo(retrievedUser.getFirstname());
//        Assertions.assertThat(savedUser.getLastname()).isEqualTo(retrievedUser.getLastname());
//        Assertions.assertThat(savedUser.getOwnerGroup()).isEqualTo(retrievedUser.getOwnerGroup());
//        Assertions.assertThat(savedUser.getRoles()).isEqualTo(retrievedUser.getRoles());
//    }
//
//    @Test
//    void getUsersByGroupId_ShouldReturnListOfUsers() {
//
//        int groupId = 1;
//
//        List<User> users = userService.getUsersByGroupId(groupId);
//
//        assertEquals(3, users.size());
//        assertEquals("admin", users.get(0).getFirstname());
//        assertEquals("alex", users.get(1).getFirstname());
//        assertEquals("John", users.get(2).getFirstname());
//    }
}
