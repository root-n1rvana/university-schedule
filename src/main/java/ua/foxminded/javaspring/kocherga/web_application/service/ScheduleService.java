package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;

import java.text.ParseException;
import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> getAllSchedules();

    List<ScheduleDto> getScheduleInDateRange(String yearMonth) throws ParseException;
}
