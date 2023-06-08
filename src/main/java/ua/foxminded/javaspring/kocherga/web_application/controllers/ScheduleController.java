package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {

    @GetMapping("/schedule")
    public String index() {
        return "schedule/schedule";
    }

    @GetMapping("/schedule-result")
    public String handleScheduleRequest(@RequestParam(name = "day", required = false) Integer day,
                                        @RequestParam(name = "month", required = false) String month,
                                        Model model) {
        if(day != null) {
            // retrieve data for selected day
//            List<Data> dataForDay = dataService.getDataForDay(day);
//            model.addAttribute("data", dataForDay);
        } else if(month != null) {
            // retrieve data for selected month
//            List<Data> dataForMonth = dataService.getDataForMonth(month);
//            model.addAttribute("data", dataForMonth);
        }
        return "result";
    }
}
