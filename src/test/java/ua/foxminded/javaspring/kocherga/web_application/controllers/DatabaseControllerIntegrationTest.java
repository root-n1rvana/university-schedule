package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DatabaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers_Controller_ShouldReturnListOfAllUsers() throws Exception {
        mockMvc.perform(get("/database/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(44)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(6L)),
                                hasProperty("userName", is("Artur")),
                                hasProperty("userLastname", is("Morozov")),
                                hasProperty("ownerGroup", hasProperty("name", is("professor"))),
                                hasProperty("ownerRole", hasProperty("roleName", is(RoleName.PROFESSOR)))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(7L)),
                                hasProperty("userName", is("Pavel")),
                                hasProperty("userLastname", is("Ivanov")),
                                hasProperty("ownerGroup", hasProperty("name", is("GR-1"))),
                                hasProperty("ownerRole", hasProperty("roleName", is(RoleName.STUDENT)))
                        )
                )));
    }
}
