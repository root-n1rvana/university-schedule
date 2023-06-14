package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    void getUsersByGroupId_Controller_ShouldReturnListOfUsers() throws Exception {
        mockMvc.perform(get("/users/{groupId}", groupId))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(3)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("userName", is("admin"))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("userName", is("alex")),
                                hasProperty("userLastname", is("Collier"))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("userName", is("John")),
                                hasProperty("userLastname", is("Collier"))
                        )
                )));
    }
}
