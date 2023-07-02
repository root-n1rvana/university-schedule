package ua.foxminded.javaspring.kocherga.web_application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.kocherga.web_application.models.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    int groupId = 1;

    @Test
    void getUsersByGroupId_ShouldReturnListOfUsers() {
        List<User> users = userService.getUsersByGroupId(groupId);

        assertEquals(3, users.size());
        assertEquals("admin", users.get(0).getUserName());
        assertEquals("alex", users.get(1).getUserName());
        assertEquals("John", users.get(2).getUserName());
    }
}
