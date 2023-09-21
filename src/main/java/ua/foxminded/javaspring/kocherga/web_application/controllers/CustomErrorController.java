package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController implements ErrorController {

    @RequestMapping("/error/401")
    public String handleUnauthorized() {
        return "error/401";
    }

    @RequestMapping("/error/403")
    public String handleForbidden() {
        return "error/403";
    }

    @RequestMapping("/error/404")
    public String handleNotFound() {
        return "error/404";
    }

    @RequestMapping("/error/405")
    public String handleMethodNotAllowed() {
        return "error/405";
    }

    @RequestMapping("/error/500")
    public String handleServerError() {
        return "error/500";
    }

    public String getErrorPath() {
        return "/error";
    }
}
