package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Test
    void getAllCourses_Controller_ShouldReturnListOfAllCourses() throws Exception {
        mockMvc.perform(get("/database/courses"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses"))
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

    @Test
    void getAllRooms_Controller_ShouldReturnListOfAllRooms() throws Exception {
        mockMvc.perform(get("/database/rooms"))
                .andExpect(status().isOk())
                .andExpect(view().name("rooms"))
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

    @Test
    void getAllGroups_Controller_ShouldReturnListOfAllGroups() throws Exception {
        mockMvc.perform(get("/database/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups"))
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attribute("groups", hasSize(8)))
                .andExpect(model().attribute("groups", hasItem(
                        allOf(
                                hasProperty("id", is(8L)),
                                hasProperty("name", is("GR-6"))
                        )
                )));
    }

    @Test
    void getAllRoles_Controller_ShouldReturnListOfAllRoles() throws Exception {
        mockMvc.perform(get("/database/roles"))
                .andExpect(status().isOk())
                .andExpect(view().name("roles"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attribute("roles", hasSize(3)))
                .andExpect(model().attribute("roles", hasItem(
                        allOf(
                                hasProperty("id", is(3L)),
                                hasProperty("roleName", is(RoleName.PROFESSOR))
                        )
                )));
    }

    @Test
    void getAllLessonsTime_Controller_ShouldReturnListOfAllLessonsTime() throws Exception {
        mockMvc.perform(get("/database/lessonsTime"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessonsTime"))
                .andExpect(model().attributeExists("lessonsTime"))
                .andExpect(model().attribute("lessonsTime", hasSize(5)))
                .andExpect(model().attribute("lessonsTime", hasItem(
                        allOf(
                                hasProperty("id", is(5L)),
                                hasProperty("lessonTime", is("15:15-16:45"))
                        )
                )));
    }

    @Test
    void getAllSchedules_Controller_ShouldReturnListOfAllSchedules() throws Exception {
        Date expectedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-12");

        mockMvc.perform(get("/database/schedules"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedules"))
                .andExpect(model().attributeExists("schedules"))
                .andExpect(model().attribute("schedules", hasSize(3)))
                .andExpect(model().attribute("schedules", hasItem(
                        allOf(
                                hasProperty("id", is(3L)),
                                hasProperty("scheduleDate", is(expectedDate))
                        )
                )));
    }

    @Test
    void getAllLessons_Controller_ShouldReturnListOfAllLessons() throws Exception {
        Date expectedDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-11");
        Date expectedDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-12");

        mockMvc.perform(get("/database/lessons"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons"))
                .andExpect(model().attributeExists("lessons"))
                .andExpect(model().attribute("lessons", hasSize(30)))
                .andExpect(model().attribute("lessons", hasItem(
                        allOf(
                                hasProperty("id", is(15L)),
                                hasProperty("ownerCourse", hasProperty("courseName", is("History"))),
                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A2"))),
                                hasProperty("ownerGroup", hasProperty("name", is("GR-3"))),
                                hasProperty("ownerSchedule", hasProperty("scheduleDate", is(expectedDate1))),
                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("15:15-16:45")))
                        )
                )))
                .andExpect(model().attribute("lessons", hasItem(
                        allOf(
                                hasProperty("id", is(30L)),
                                hasProperty("ownerCourse", hasProperty("courseName", is("Chemistry"))),
                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A8"))),
                                hasProperty("ownerGroup", hasProperty("name", is("GR-6"))),
                                hasProperty("ownerSchedule", hasProperty("scheduleDate", is(expectedDate2))),
                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("15:15-16:45")))
                        )
                )));
    }
}
