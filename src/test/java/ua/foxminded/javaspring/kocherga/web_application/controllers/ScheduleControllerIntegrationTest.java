package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.repository.LessonRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ScheduleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    LessonRepository lessonRepository;

    private final long ownerGroupId = 9L;
    private final long ownerCourseId = 1L;
    private final long ownerRoomId = 1L;
    private final long ownerLessonTimeId = 1L;
    private final String newScheduleDate = "2023-11-01";

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testSchedulePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("schedule"));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testScheduleManagementPageEmptyParam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/management"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("management/schedule-management"))
                .andExpect(model().attributeExists("scheduleInDateRange"));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testScheduleManagementPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/management")
                        .param("yearMonth", "2023-09"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("management/schedule-management"))
                .andExpect(model().attributeExists("scheduleInDateRange"))
                .andExpect(model().attribute("scheduleInDateRange", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("lessons", hasItem(
                                        allOf(
                                                hasProperty("id", is(11L)),
                                                hasProperty("ownerCourse", hasProperty("courseName", is("Geography"))),
                                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A3"))),
                                                hasProperty("ownerGroup", hasProperty("name", is("GR-3"))),
                                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("8:00-9:30")))
                                        )
                                )),
                                hasProperty("lessons", hasItem(
                                        allOf(
                                                hasProperty("id", is(20L)),
                                                hasProperty("ownerCourse", hasProperty("courseName", is("Physics"))),
                                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A4"))),
                                                hasProperty("ownerGroup", hasProperty("name", is("GR-4"))),
                                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("15:15-16:45")))
                                        )
                                ))
                        )
                )));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testAddLesson_ProfessorAccess() throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expectedDate = LocalDate.parse(newScheduleDate, dateFormatter);

        // Verify that the Lesson does not exist
        assertFalse(lessonRepository.existsByOwnerGroupIdAndOwnerLessonTimeIdAndOwnerScheduleScheduleDate(ownerGroupId, ownerLessonTimeId, expectedDate));

        mockMvc.perform(post("/schedule/addLesson")
                        .param("newScheduleDate", newScheduleDate)
                        .param("ownerGroup.id", String.valueOf(ownerGroupId))
                        .param("ownerCourse.id", String.valueOf(ownerCourseId))
                        .param("ownerRoom.id", String.valueOf(ownerRoomId))
                        .param("ownerLessonTime.id", String.valueOf(ownerLessonTimeId))
                        .param("professor.id", String.valueOf(2)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedule/management"))
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(flash().attribute("successMessage", "Lesson added successfully!"));

        // Verify that the group was added to the database
        assertTrue(lessonRepository.existsByOwnerGroupIdAndOwnerLessonTimeIdAndOwnerScheduleScheduleDate(ownerGroupId, ownerLessonTimeId, expectedDate));

        // Cleaning after test
        lessonRepository.deleteByOwnerGroupIdAndOwnerLessonTimeIdAndOwnerScheduleScheduleDate(ownerGroupId, ownerLessonTimeId, expectedDate);
        scheduleRepository.delete(scheduleRepository.getByScheduleDate(expectedDate));
        assertFalse(lessonRepository.existsByOwnerGroupIdAndOwnerLessonTimeIdAndOwnerScheduleScheduleDate(ownerGroupId, ownerLessonTimeId, expectedDate));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testAddLesson_StudentAccess() throws Exception {

        mockMvc.perform(post("/schedule/addLesson")
                        .param("newScheduleDate", newScheduleDate)
                        .param("ownerGroup.id", String.valueOf(ownerGroupId))
                        .param("ownerCourse.id", String.valueOf(ownerCourseId))
                        .param("ownerRoom.id", String.valueOf(ownerRoomId))
                        .param("ownerLessonTime.id", String.valueOf(ownerLessonTimeId)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testUpdateLesson() throws Exception {

        // Pulling out a test lesson
        Lesson testLesson = lessonRepository.getLessonById(31L);

        // Prepare the updated data
        String expectedDate = "2023-09-05";

        // Checking that updating data is not the same with data from database
        assertNotEquals(testLesson.getOwnerSchedule().getScheduleDate().toString(), expectedDate);

        mockMvc.perform(post("/schedule/update")
                        .param("id", "31")
                        .param("newScheduleDate", expectedDate)
                        .param("ownerGroup.id", "3")
                        .param("ownerCourse.id", "7")
                        .param("ownerRoom.id", "1")
                        .param("ownerLessonTime.id", "1")
                        .param("professor.id", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedule/management"))
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(flash().attribute("successMessage", "Lesson updated successfully!"));

        // Verify that the Lesson was updated in the database
        Lesson actualLesson = lessonRepository.getLessonById(31L);
        assertEquals(expectedDate, actualLesson.getOwnerSchedule().getScheduleDate().toString());

        // Revert changes done
        Lesson lesson = lessonRepository.getLessonById(31L);
        lesson.setOwnerSchedule(scheduleRepository.getReferenceById(4));
        lessonRepository.save(lesson);
        assertEquals(lesson.getOwnerSchedule().getId(), 4L);
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testUpdateLessonError() throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Prepare the updated data
        String expectedDate = "2023-09-04";
        LocalDate date = LocalDate.parse(expectedDate, dateFormatter);
        long ownerGroupId = 3L;
        long ownerLessonTimeId = 1L;

        // Check if lesson for this group Date & Time already exist
        assertTrue(lessonRepository.existsByOwnerGroupIdAndOwnerLessonTimeIdAndOwnerScheduleScheduleDate(ownerGroupId, ownerLessonTimeId, date));

        mockMvc.perform(post("/schedule/update")
                        .param("id", "31")
                        .param("newScheduleDate", expectedDate)
                        .param("ownerGroup.id", String.valueOf(ownerGroupId))
                        .param("ownerCourse.id", "7")
                        .param("ownerRoom.id", "1")
                        .param("ownerLessonTime.id", String.valueOf(ownerLessonTimeId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedule/management"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Lesson for this group with same Date and Time already exists"));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testDeleteLesson() throws Exception {

        Lesson lessonToDelete = lessonRepository.getLessonById(60L);

        // Check if lesson exist
        assertNotNull(lessonRepository.getLessonById(60L));

        mockMvc.perform(post("/schedule/delete")
                        .param("lessonId", String.valueOf(lessonToDelete.getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedule/management"))
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(flash().attribute("successMessage", "Lesson deleted successfully!"));

        // Verify that the group was deleted from the database
        assertNull(lessonRepository.getLessonById(60L));

        // Reverting
        lessonRepository.save(lessonToDelete);
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testDeleteLessonError() throws Exception {
        long nonExistinglessonId = 65L;

        // Verify that this Lesson does not exist in database
        assertFalse(lessonRepository.existsById(nonExistinglessonId));

        mockMvc.perform(post("/schedule/delete")
                        .param("lessonId", String.valueOf(nonExistinglessonId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/schedule/management"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Lesson not found or could not be deleted"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testScheduleInDateRangeForGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/group/month")
                        .param("groupId", "3")
                        .param("yearMonth", "2023-10"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("schedule"))
                .andExpect(model().attributeExists("groupScheduleByMonth"))
                .andExpect(model().attribute("groupScheduleByMonth", hasItem(
                        allOf(
                                hasProperty("id", is(4L)),
                                hasProperty("lessons", hasItem(
                                        allOf(
                                                hasProperty("id", is(31L)),
                                                hasProperty("ownerCourse", hasProperty("courseName", is("Math"))),
                                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A1"))),
                                                hasProperty("ownerGroup", hasProperty("name", is("GR-1"))),
                                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("8:00-9:30")))
                                        )
                                ))
                        )
                )));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testScheduleInDateRangeForTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/teacher/month")
                        .param("professorLogin", "teach1")
                        .param("yearMonth", "2023-10"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("schedule"))
                .andExpect(model().attributeExists("teacherScheduleByMonth"))
                .andExpect(model().attribute("teacherScheduleByMonth", hasItem(
                        allOf(
                                hasProperty("id", is(6L)),
                                hasProperty("lessons", hasItem(
                                        allOf(
                                                hasProperty("id", is(58L)),
                                                hasProperty("ownerCourse", hasProperty("courseName", is("History"))),
                                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A4"))),
                                                hasProperty("ownerGroup", hasProperty("name", is("GR-6"))),
                                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("11:30-13:00")))
                                        )
                                ))
                        )
                )));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testScheduleForDayForGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/group/day")
                        .param("groupId", "3")
                        .param("yearMonthDay", "2023-10-10"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("schedule"))
                .andExpect(model().attributeExists("groupScheduleByDay"))
                .andExpect(model().attribute("groupScheduleByDay", hasItem(
                        allOf(
                                hasProperty("id", is(4L)),
                                hasProperty("lessons", hasItem(
                                        allOf(
                                                hasProperty("id", is(33L)),
                                                hasProperty("ownerCourse", hasProperty("courseName", is("Geography"))),
                                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A3"))),
                                                hasProperty("ownerGroup", hasProperty("name", is("GR-1"))),
                                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("11:30-13:00")))
                                        )
                                ))
                        )
                )));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testScheduleForDayForTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/teacher/day")
                        .param("professorLogin", "teach1")
                        .param("yearMonthDay", "2023-10-12"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("schedule"))
                .andExpect(model().attributeExists("teacherScheduleByDay"))
                .andExpect(model().attribute("teacherScheduleByDay", hasItem(
                        allOf(
                                hasProperty("id", is(6L)),
                                hasProperty("lessons", hasItem(
                                        allOf(
                                                hasProperty("id", is(58L)),
                                                hasProperty("ownerCourse", hasProperty("courseName", is("History"))),
                                                hasProperty("ownerRoom", hasProperty("roomLabel", is("A4"))),
                                                hasProperty("ownerGroup", hasProperty("name", is("GR-6"))),
                                                hasProperty("ownerLessonTime", hasProperty("lessonTime", is("11:30-13:00")))
                                        )
                                ))
                        )
                )));
    }
}
