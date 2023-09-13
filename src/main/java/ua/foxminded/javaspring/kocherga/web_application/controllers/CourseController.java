package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final static String COURSE_MANAGEMENT_PAGE = "management/course-management";
    private final static String REDIRECT_TO_COURSE_MANAGEMENT_PAGE = "redirect:/course/management";
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/management")
    public String showCourseManagementPage(Model model) {
        List<CourseDto> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);
        return COURSE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update")
    public String updateCourse(@RequestParam("courseId") long courseId, @RequestParam("courseName") String courseName,
                               @RequestParam("courseDescription") String courseDescription) {
        CourseDto courseDto = courseService.getCourseById(courseId);
        courseDto.setCourseName(courseName);
        courseDto.setCourseDescription(courseDescription);
        courseService.save(courseDto);
        return REDIRECT_TO_COURSE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESSOR')")
    @PostMapping("/addCourse")
    public String addCourse(@RequestParam String newCourseName, @RequestParam String newCourseDescription, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = courseService.saveWithRedirAttr(newCourseName, newCourseDescription);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_COURSE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public String deleteCourse(@RequestParam long courseId, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = courseService.deleteWithRedirAttr(courseId);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_COURSE_MANAGEMENT_PAGE;
    }
}
