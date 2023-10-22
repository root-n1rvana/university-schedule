package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> getAllSchedules();

    List<ScheduleDto> getScheduleInDateRange(String yearMonth);

    List<ScheduleDto> getGroupScheduleByMonth(ScheduleDto scheduleDto, BindingResult bindingResult);

    List<ScheduleDto> getGroupScheduleByDay(ScheduleDto scheduleDto);

    List<ScheduleDto> getTeacherScheduleByMonth(ScheduleDto scheduleDto);

    List<ScheduleDto> getTeacherScheduleByDay(ScheduleDto scheduleDto);
}
