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

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testFindCourse_AdminAccess() throws Exception {
        String courseName = "Math";
        mockMvc.perform(get("/course/find-by-name")
                        .param("courseName", courseName))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("management/course-management"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("course"));
    }

    @WithMockUser(roles = "STUDENT")
    @Test
    public void testFindCourse_StudentAccess() throws Exception {
        String courseName = "Math";

        mockMvc.perform(get("/find-by-name")
                        .param("courseName", courseName))
                .andExpect(status().isForbidden()); // Expect 403 Forbidden for STUDENT role
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateCourse() throws Exception {
        // Create a test course
        Course testCourse = new Course();
        testCourse.setCourseName("Test Course");
        testCourse.setCourseDescription("Description");
        courseService.save(testCourse);
        Course savedCourse = courseService.findByCourseName("Test Course");

        // Prepare the updated data
        String updatedCourseName = "Updated Course Name";
        String updatedCourseDescription = "Updated Description";

        mockMvc.perform(post("/course")
                        .param("courseId", String.valueOf(savedCourse.getId()))
                        .param("courseName", updatedCourseName)
                        .param("courseDescription", updatedCourseDescription))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course/management"));

        // Verify that the course was updated in the database
        Course updatedCourse = courseService.getCourseById(savedCourse.getId());
        assertEquals(updatedCourseName, updatedCourse.getCourseName());
        assertEquals(updatedCourseDescription, updatedCourse.getCourseDescription());

        // Cleaning after test
        courseService.deleteByCourseName("Updated Course Name");
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testAddCourse_AdminOrProfessorAccess() throws Exception {
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
    @WithMockUser(roles = "STUDENT")
    public void testAddCourse_StudentAccess() throws Exception {
        String newCourseName = "New Course";
        String newCourseDescription = "New Course Description";

        mockMvc.perform(post("/course/addCourse")
                        .param("newCourseName", newCourseName)
                        .param("newCourseDescription", newCourseDescription))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteCourse_AdminAccess() throws Exception {
        String courseNameToDelete = "CourseToDelete";
        String courseDescriptionToDelete = "CourseDescriptionToDelete";

        // Create a test course to be deleted
        Course testCourse = new Course();
        testCourse.setCourseName(courseNameToDelete);
        testCourse.setCourseDescription(courseDescriptionToDelete);
        courseService.save(testCourse);

        mockMvc.perform(post("/course/deleteCourse")
                        .param("courseNameToDelete", courseNameToDelete))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course/management"))
                .andExpect(flash().attributeExists("deletionSucceeded"))
                .andExpect(flash().attribute("deletionSucceeded", "Course deleted successfully!"));

        // Verify that the course was deleted from the database
        assertFalse(courseService.existsByCourseName(courseNameToDelete));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteCourse_CourseNotFound() throws Exception {
        String courseNameToDelete = "NonExistentCourse";

        mockMvc.perform(post("/course/deleteCourse")
                        .param("courseNameToDelete", courseNameToDelete))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/course/management"))
                .andExpect(flash().attributeExists("deletionError"))
                .andExpect(flash().attribute("deletionError", "Course not found or could not be deleted."));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testDeleteCourse_StudentAccess() throws Exception {
        String courseNameToDelete = "CourseToDelete";

        mockMvc.perform(post("/course/deleteCourse")
                        .param("courseNameToDelete", courseNameToDelete))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }

}
