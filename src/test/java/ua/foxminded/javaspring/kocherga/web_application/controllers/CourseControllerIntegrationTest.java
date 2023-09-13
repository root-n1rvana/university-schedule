package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.service.CourseService;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseService courseService;

    @WithMockUser("spring")
    @Test
    public void testCoursePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/course/management"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("management/course-management"))
                .andExpect(model().attributeExists("allCourses"))
                .andExpect(model().attribute("allCourses", hasSize(10)))
                .andExpect(model().attribute("allCourses", hasItem(
                        allOf(
                                hasProperty("id", is(10L)),
                                hasProperty("courseName", is("Physics"))
                        )
                )));
    }

    @WithMockUser(authorities = "STUDENT")
    @Test
    public void testFindCourse_StudentAccess() throws Exception {
        String courseName = "Math";

        mockMvc.perform(get("/find-by-name")
                        .param("courseName", courseName))
                .andExpect(status().isForbidden()); // Expect 403 Forbidden for STUDENT role
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testUpdateCourse() throws Exception {
        // Create a test course
        Course testCourse = new Course();
        testCourse.setCourseName("Test Course");
        testCourse.setCourseDescription("Description");
        courseService.save(testCourse);
        CourseDto savedCourse = courseService.findByCourseName("Test Course");

        // Prepare the updated data
        String expectedCourseName = "Updated Course Name";
        String expectedCourseDescription = "Updated Description";

        mockMvc.perform(post("/course/update")
                        .param("courseId", String.valueOf(savedCourse.getId()))
                        .param("courseName", expectedCourseName)
                        .param("courseDescription", expectedCourseDescription))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course/management"));

        // Verify that the course was updated in the database
        CourseDto actualCourse = courseService.getCourseById(savedCourse.getId());
        assertEquals(expectedCourseName, actualCourse.getCourseName());
        assertEquals(expectedCourseDescription, actualCourse.getCourseDescription());

        // Cleaning after test
        courseService.deleteByCourseName("Updated Course Name");
    }

    @Test
    @WithMockUser(authorities = "PROFESSOR")
    public void testAddCourse_ProfessorAccess() throws Exception {
        String newCourseName = "New Course";
        String newCourseDescription = "New Course Description";

        mockMvc.perform(post("/course/addCourse")
                        .param("newCourseName", newCourseName)
                        .param("newCourseDescription", newCourseDescription))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course/management"))
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(flash().attribute("successMessage", "Course added successfully!"));

        // Verify that the course was added to the database
        assertTrue(courseService.existsByCourseName(newCourseName));

        // Cleaning after test
        courseService.deleteByCourseName(newCourseName);
        assertFalse(courseService.existsByCourseName(newCourseName));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testAddExistingCourse() throws Exception {
        String newCourseName = "Math";
        String newCourseDescription = "New Course Description";

        mockMvc.perform(post("/course/addCourse")
                        .param("newCourseName", newCourseName)
                        .param("newCourseDescription", newCourseDescription))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course/management"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Course with the same name already exists."));
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void testAddCourse_StudentAccess() throws Exception {
        String newCourseName = "New Course";
        String newCourseDescription = "New Course Description";

        mockMvc.perform(post("/course/addCourse")
                        .param("newCourseName", newCourseName)
                        .param("newCourseDescription", newCourseDescription))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testDeleteCourse_AdminAccess() throws Exception {
        // Create a test course to be deleted
        Course testCourse = new Course();
        testCourse.setCourseName("CourseToDelete");
        testCourse.setCourseDescription("CourseDescriptionToDelete");
        courseService.save(testCourse);

        // Verify that the course was saved to database
        assertTrue(courseService.existsByCourseName(testCourse.getCourseName()));

        mockMvc.perform(post("/course/delete")
                        .param("courseId", String.valueOf(testCourse.getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course/management"))
                .andExpect(flash().attributeExists("deletionSucceeded"))
                .andExpect(flash().attribute("deletionSucceeded", "Course deleted successfully!"));

        // Verify that the course was deleted from the database
        assertFalse(courseService.existsByCourseName(testCourse.getCourseName()));
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void testDeleteCourse_StudentAccess() throws Exception {
        String courseNameToDelete = "CourseToDelete";

        mockMvc.perform(post("/course/delete")
                        .param("courseNameToDelete", courseNameToDelete))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }
}
