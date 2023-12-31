package ua.foxminded.javaspring.kocherga.web_application.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.CourseServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final static String COURSE_MANAGEMENT_PAGE = "management/course-management";
    private final static String REDIRECT_TO_COURSE_MANAGEMENT_PAGE = "redirect:/course/management";
    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/management")
    public String showCourseManagementPage(Model model) {
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);
        return COURSE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addCourse")
    public String addCourse(@ModelAttribute @Valid CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        courseService.saveNewCourse(courseDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_COURSE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update")
    public String updateCourse(@Valid CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        courseService.updateCourse(courseDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_COURSE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete")
    public String deleteCourse(@RequestParam long courseId, RedirectAttributes redirectAttributes) {
        courseService.deleteCourse(courseId, redirectAttributes);
        return REDIRECT_TO_COURSE_MANAGEMENT_PAGE;
    }
}
