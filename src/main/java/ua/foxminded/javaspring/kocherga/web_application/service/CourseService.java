package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;

import java.util.List;

public interface CourseService {

    void save(Course course);

    void save(CourseDto courseDto);

    CourseDto getCourseById(long courseId);

    CourseDto findByCourseName(String courseName);

    List<CourseDto> getAllCourses();

    RedirectAttributesDto saveAndGetRedirAttr(CourseDto courseDto);

    RedirectAttributesDto updateAndGetRedirAttr(CourseDto courseDto);

    boolean existsByCourseName(String courseName);

    boolean existByCourseId(Long courseid);

    RedirectAttributesDto deleteAndGetRedirAttr(Long courseId);
}
