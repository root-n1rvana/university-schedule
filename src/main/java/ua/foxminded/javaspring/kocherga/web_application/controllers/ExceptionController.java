package ua.foxminded.javaspring.kocherga.web_application.controllers;


import jakarta.validation.ValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionController {

    private final static String REDIRECT_TO_COURSE_MANAGEMENT_PAGE = "redirect:/course/management";
    @ExceptionHandler(ValidationException.class)
    public String validationException(ValidationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return REDIRECT_TO_COURSE_MANAGEMENT_PAGE;
    }
}
