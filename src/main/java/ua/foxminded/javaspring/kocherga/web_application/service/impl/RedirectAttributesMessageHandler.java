package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class RedirectAttributesMessageHandler {

    void setErrorMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("errorMessage", message);
    }

    void setSuccessMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("successMessage", message);
    }

    void setWarningMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("warningMessage", message);
    }
}
