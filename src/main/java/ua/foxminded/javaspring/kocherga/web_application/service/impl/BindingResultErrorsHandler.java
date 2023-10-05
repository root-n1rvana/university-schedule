package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.validation.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

@Component
public class BindingResultErrorsHandler {

    public void validateBindingResultErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            throw new ValidationException(errMessage);
        }
    }
}
