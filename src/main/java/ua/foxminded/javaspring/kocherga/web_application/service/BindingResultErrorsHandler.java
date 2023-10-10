package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.*;

import java.util.stream.Collectors;

@Component
public class BindingResultErrorsHandler {

    public void validateCourseBindingResultErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new CourseValidationException(errMessage);
        }
    }

    public void validateUserBindingResultErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new UserValidationException(errMessage);
        }
    }

    public void validateLessonBindingResultErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new LessonValidationException(errMessage);
        }
    }

    public void validateGroupBindingResultErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new GroupValidationException(errMessage);
        }
    }

    public void RegistrationBindingResultErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new RegistrationValidationException(errMessage);
        }
    }
}