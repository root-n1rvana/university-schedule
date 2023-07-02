package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {

    @GetMapping("/schedule")
    public String index() {
        return "schedule";
    }

}
