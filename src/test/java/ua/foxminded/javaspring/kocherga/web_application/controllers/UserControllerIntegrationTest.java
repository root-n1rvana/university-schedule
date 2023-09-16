package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    int groupId = 1;

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testManagementPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/management"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("management/user-management"));
    }

    @WithMockUser(roles = "STUDENT")
    @Test
    public void testShowManagementPageUnauthorized() throws Exception {
        mockMvc.perform(get("/user/management"))
                .andExpect(status().isForbidden()); // Expects HTTP 403 Forbidden
    }

    @WithUserDetails(value = "teach1")
    @Test
    void getUsersByGroupId_Controller_ShouldReturnListOfUsers() throws Exception {
        mockMvc.perform(get("/user/{groupId}", groupId))
                .andExpect(status().isOk())
                .andExpect(view().name("db/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(3)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("firstname", is("admin"))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("firstname", is("alex")),
                                hasProperty("lastname", is("Collier"))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("firstname", is("John")),
                                hasProperty("lastname", is("Collier"))
                        )
                )));
    }
}
