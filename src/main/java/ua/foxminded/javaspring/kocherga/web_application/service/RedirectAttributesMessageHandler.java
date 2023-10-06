package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class RedirectAttributesMessageHandler {

    public void setErrorMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("errorMessage", message);
    }

    public void setSuccessMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("successMessage", message);
    }

    public void setWarnMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("warnMessage", message);
    }
}
