package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;

import java.util.List;

public interface CourseService {

    CourseDto getCourseById(long courseId);

    CourseDto findByCourseName(String courseName);

    List<CourseDto> getAllCourses();

    void saveAndGetRedirAttr(CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    void updateAndGetRedirAttr(CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    boolean existsByCourseName(String courseName);

    boolean existByCourseId(Long courseid);

    RedirectAttributesDto deleteAndGetRedirAttr(Long courseId);
}
