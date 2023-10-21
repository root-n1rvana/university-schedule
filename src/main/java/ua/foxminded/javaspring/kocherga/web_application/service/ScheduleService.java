package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> getAllSchedules();

    List<ScheduleDto> getScheduleInDateRange(String yearMonth);

    List<ScheduleDto> getScheduleInDateRangeForGroup(ScheduleDto scheduleDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    List<ScheduleDto> getScheduleForDayForGroup(ScheduleDto scheduleDto);

    List<ScheduleDto> getScheduleInDateRangeForTeacher(ScheduleDto scheduleDto);

    List<ScheduleDto> getScheduleForDayForTeacher(ScheduleDto scheduleDto);
}
