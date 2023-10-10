package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> getAllCourses();

    @Transactional
    void saveNewCourse(CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void updateCourse(CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void deleteCourse(Long courseId, RedirectAttributes redirectAttributes);
}
