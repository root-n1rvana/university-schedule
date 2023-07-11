package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.service.LessonService;

import java.util.List;

@Controller
//@RequestMapping("/lessons")
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

//    @GetMapping("/lessons")
//    public String getLessonsByGroupIdAndScheduleId(@RequestParam("groupId") int groupId, @RequestParam("scheduleId") int scheduleId, Model model) {
//        List<Lesson> lessons = lessonService.getLessonsByGroupIdAndScheduleId(groupId, scheduleId);
//        model.addAttribute("lessons", lessons);
//        return "lessons";
//    }

}