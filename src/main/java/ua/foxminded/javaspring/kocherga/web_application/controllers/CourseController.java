package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/management")
    public String showCourseManagementPage(Model model) {
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);
        return "management/course-management";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/find-by-name")
    public String findCourse(@RequestParam("courseName") String courseName, Model model) {
        Course course = courseService.findByCourseName(courseName);
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);
        model.addAttribute("course", course);
        return "management/course-management";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String updateCourse(@RequestParam("courseId") long courseId, @RequestParam("courseName") String courseName,
                               @RequestParam("courseDescription") String courseDescription) {
        Course course = courseService.getCourseById(courseId);
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);
        courseService.save(course);
        return "redirect:/course/management";
    }

    @PreAuthorize("hasAnyRoles('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addCourse")
    public String addCourse(@RequestParam String newCourseName, @RequestParam String newCourseDescription, RedirectAttributes redirectAttributes) {
        if (courseService.existsByCourseName(newCourseName)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Course with the same name already exists.");
        } else {
            Course newCourse = new Course();
            newCourse.setCourseName(newCourseName);
            newCourse.setCourseDescription(newCourseDescription);
            courseService.save(newCourse);
            redirectAttributes.addFlashAttribute("successMessage", "Course added successfully!");
        }
        return "redirect:/course/management";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteCourse")
    public String deleteCourse(@RequestParam String courseNameToDelete, RedirectAttributes redirectAttributes) {
        if (courseService.existsByCourseName(courseNameToDelete)) {
            courseService.deleteByCourseName(courseNameToDelete);
            redirectAttributes.addFlashAttribute("deletionSucceeded", "Course deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("deletionError", "Course not found or could not be deleted.");
        }
        return "redirect:/course/management";
    }
}
