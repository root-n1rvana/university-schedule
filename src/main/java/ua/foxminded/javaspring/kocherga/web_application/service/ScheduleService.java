package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ScheduleService {

    List<Schedule> getAllSchedules();

    List<Schedule> getScheduleInDateRange(String yearMonth) throws ParseException;
}
