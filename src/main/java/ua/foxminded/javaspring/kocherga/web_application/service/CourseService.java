package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course getCourseById(long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
