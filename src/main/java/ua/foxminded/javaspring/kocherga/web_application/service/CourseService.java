package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
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

    public Course findByCourseName(String courseName) {
        return courseRepository.getCourseByCourseName(courseName);
    }

    @Transactional
    public void save(Course course) {
        courseRepository.save(course);
    }

    public boolean existsByCourseName(String courseName) {
        return courseRepository.existsByCourseName(courseName);
    }

    @Transactional
    public void deleteByCourseName(String courseName) {
        courseRepository.deleteByCourseName(courseName);
    }
}
