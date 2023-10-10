package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DatabaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser("spring")
    @Test
    public void testEntityPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entity"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("db/entity"));
    }

    @WithMockUser("spring")
    @Test
    void getAllUsers_Controller_ShouldReturnListOfAllUsers() throws Exception {
        mockMvc.perform(get("/entity/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("db/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(44)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(6L)),
                                hasProperty("firstname", is("Artur")),
                                hasProperty("lastname", is("Morozov")),
                                hasProperty("ownerGroup", hasProperty("name", is("professor"))),
                                hasProperty("roles", hasItem(
                                        allOf(hasProperty("roleName", is(RoleName.ROLE_PROFESSOR))
                                        )))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(7L)),
                                hasProperty("firstname", is("Pavel")),
                                hasProperty("lastname", is("Ivanov")),
                                hasProperty("ownerGroup", hasProperty("name", is("GR-1"))),
                                hasProperty("roles", hasItem(
                                        allOf(hasProperty("roleName", is(RoleName.ROLE_STUDENT))
                                        )))
                        )
                )));
    }

    @WithMockUser("spring")
    @Test
    void getAllCourses_Controller_ShouldReturnListOfAllCourses() throws Exception {
        mockMvc.perform(get("/entity/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("db/courses"))
                .andExpect(model().attributeExists("courses"))
                .andExpect(model().attribute("courses", hasSize(10)))
                .andExpect(model().attribute("courses", hasItem(
                        allOf(
                                hasProperty("id", is(10L)),
                                hasProperty("courseName", is("Physics")),
                                hasProperty("courseDescription", is("science that deals with the structure of matter"))
                        )
                )));
    }

    @WithMockUser("spring")
    @Test
    void getAllRooms_Controller_ShouldReturnListOfAllRooms() throws Exception {
        mockMvc.perform(get("/entity/rooms"))
                .andExpect(status().isOk())
                .andExpect(view().name("db/rooms"))
                .andExpect(model().attributeExists("rooms"))
                .andExpect(model().attribute("rooms", hasSize(10)))
                .andExpect(model().attribute("rooms", hasItem(
                        allOf(
                                hasProperty("id", is(10L)),
                                hasProperty("roomLabel", is("A0")),
                                hasProperty("roomDescription", is("Some description"))
                        )
                )));
    }

    @WithMockUser("spring")
    @Test
    void getAllGroups_Controller_ShouldReturnListOfAllGroups() throws Exception {
        mockMvc.perform(get("/entity/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("db/groups"))
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attribute("groups", hasSize(9)))
                .andExpect(model().attribute("groups", hasItem(
                        allOf(
                                hasProperty("id", is(8L)),
                                hasProperty("name", is("GR-6"))
                        )
                )));
    }

    @WithMockUser("spring")
    @Test
    void getAllRoles_Controller_ShouldReturnListOfAllRoles() throws Exception {
        mockMvc.perform(get("/entity/roles"))
                .andExpect(status().isOk())
                .andExpect(view().name("db/roles"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attribute("roles", hasSize(3)))
                .andExpect(model().attribute("roles", hasItem(
                        allOf(
                                hasProperty("id", is(3L)),
                                hasProperty("roleName", is(RoleName.ROLE_PROFESSOR))
                        )
                )));
    }

    @WithMockUser("spring")
    @Test
    void getAllLessonsTime_Controller_ShouldReturnListOfAllLessonsTime() throws Exception {
        mockMvc.perform(get("/entity/lessonsTime"))
                .andExpect(status().isOk())
                .andExpect(view().name("db/lessonsTime"))
                .andExpect(model().attributeExists("lessonsTime"))
                .andExpect(model().attribute("lessonsTime", hasSize(5)))
                .andExpect(model().attribute("lessonsTime", hasItem(
                        allOf(
                                hasProperty("id", is(5L)),
                                hasProperty("lessonTime", is("15:15-16:45"))
                        )
                )));
    }

    @WithMockUser("spring")
    @Test
    void getAllSchedules_Controller_ShouldReturnListOfAllSchedules() throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expectedDate = LocalDate.parse("2023-09-06", dateFormatter);

        mockMvc.perform(get("/entity/schedules"))
                .andExpect(status().isOk())
                .andExpect(view().name("db/schedules"))
                .andExpect(model().attributeExists("schedules"))
                .andExpect(model().attribute("schedules", hasSize(6)))
                .andExpect(model().attribute("schedules", hasItem(
                        allOf(
                                hasProperty("id", is(3L)),
                                hasProperty("scheduleDate", is(expectedDate))
                        )
                )));
    }

    @WithMockUser("spring")
    @Test
    void getAllLessons_Controller_ShouldReturnListOfAllLessons() throws Exception {
        String expectedGroup = "GR-3";
        String expectedGroup1 = "GR-6";

        mockMvc.perform(get("/entity/lessons"))
                .andExpect(status().isOk())
                .andExpect(view().name("db/lessons"))
                .andExpect(model().attributeExists("lessons"))
                .andExpect(model().attribute("lessons", hasSize(60)))
                .andExpect(model().attribute("lessons", hasItem(
                        allOf(
                                hasProperty("id", is(15L)),
                                hasProperty("ownerCourse", hasProperty("courseName", is("History"))),
                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A2"))),
                                hasProperty("ownerGroup", hasProperty("name", is(expectedGroup))),
                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("15:15-16:45")))
                        )
                )))
                .andExpect(model().attribute("lessons", hasItem(
                        allOf(
                                hasProperty("id", is(30L)),
                                hasProperty("ownerCourse", hasProperty("courseName", is("Chemistry"))),
                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A8"))),
                                hasProperty("ownerGroup", hasProperty("name", is(expectedGroup1))),
                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("15:15-16:45")))
                        )
                )));
    }
}
