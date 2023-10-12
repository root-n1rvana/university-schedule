package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> getAllSchedules();

    List<ScheduleDto> getScheduleInDateRange(String yearMonth);

    List<ScheduleDto> getScheduleInDateRangeForGroupQuery(Long groupId, String yearMonth);

    List<ScheduleDto> getScheduleInDateRangeForGroup(Long groupId, String yearMonth);
}
