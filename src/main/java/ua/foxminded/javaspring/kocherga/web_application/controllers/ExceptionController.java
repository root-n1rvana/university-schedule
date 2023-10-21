package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.*;

@ControllerAdvice
public class ExceptionController {

    private final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(CourseValidationException.class)
    public String courseValidationException(CourseValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/course/management";
    }

    @ExceptionHandler(UserValidationException.class)
    public String studentValidationException(UserValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/user/user-management";
    }

    @ExceptionHandler(TeacherValidationException.class)
    public String teacherValidationException(TeacherValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/user/teacher-management";
    }

    @ExceptionHandler(StudentValidationException.class)
    public String studentValidationException(StudentValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/user/student-management";
    }

    @ExceptionHandler(GroupValidationException.class)
    public String groupValidationException(GroupValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/group/management";
    }

    @ExceptionHandler(RegistrationValidationException.class)
    public String registrationValidationException(RegistrationValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/register";
    }

    @ExceptionHandler(LessonValidationException.class)
    public String lessonValidationException(LessonValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/schedule/management";
    }

    @ExceptionHandler(ScheduleValidationException.class)
    public String scheduleValidationException(ScheduleValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/schedule";
    }

    @ExceptionHandler(Exception.class)
    public String processGeneralException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "something goes wrong; " + ex.getMessage());
        LOG.error(ex.getMessage());
        return "redirect:/";
    }
}
