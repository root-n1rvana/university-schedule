package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.ScheduleMapper;

import java.text.ParseException;
import java.util.List;

public interface ScheduleService {

    List<Schedule> getAllSchedules();

    List<ScheduleDto> getScheduleInDateRange(String yearMonth) throws ParseException;
}
