package ua.foxminded.javaspring.kocherga.web_application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void saveAndRetrieveCourse() {
        //given
        String courseName = "Test Course";
        String courseDescription = "This is a test course";

        // Create a test course
        Course testCourse = new Course();
        testCourse.setCourseName(courseName);
        testCourse.setCourseDescription(courseDescription);

        // Save the course using the repository
        Course savedCourse = courseRepository.save(testCourse);

        // Retrieve the room from the database
        Course retrievedCourse = courseService.getCourseById(savedCourse.getId());

        // Assert against the created room
        Assertions.assertThat(savedCourse.getId()).isEqualTo(retrievedCourse.getId());
        Assertions.assertThat(savedCourse.getCourseName()).isEqualTo(retrievedCourse.getCourseName());
        Assertions.assertThat(savedCourse.getCourseDescription()).isEqualTo(retrievedCourse.getCourseDescription());
    }
}
