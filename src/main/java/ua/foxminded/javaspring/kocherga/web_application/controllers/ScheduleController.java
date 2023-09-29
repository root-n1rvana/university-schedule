package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.ScheduleServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleServiceImpl scheduleService;

    public ScheduleController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

//    @GetMapping("/management")
//    public String showScheduleManagementPage(Model model, Date startDate, Date endDate) {
//        List<Schedule> scheduleInDateRange = scheduleService.getScheduleInDateRange(startDate, endDate);
//        model.addAttribute("scheduleInDateRange", scheduleInDateRange);
//        return "management/schedule-management";
//    }

    @GetMapping
    public String index() {
        return "schedule";
    }

    @GetMapping("/management")
    public String showScheduleManagementPage(String yearMonth, Model model) throws ParseException {
        System.out.println("==============");
        System.out.println(yearMonth);
        List<Schedule> scheduleInDateRange = scheduleService.getScheduleInDateRange(yearMonth);
        model.addAttribute("scheduleInDateRange", scheduleInDateRange);
        return "management/schedule-management";
    }

    @PostMapping("/fFive")
    public String fFive(Model model, @RequestParam String yearMonth) throws ParseException {
        System.out.println(yearMonth);
        List<Schedule> scheduleInDateRange = scheduleService.getScheduleInDateRange(yearMonth);
        model.addAttribute("scheduleInDateRange", scheduleInDateRange);
//        return "redirect:/schedule/management";
        return "management/schedule-management";
    }


}
