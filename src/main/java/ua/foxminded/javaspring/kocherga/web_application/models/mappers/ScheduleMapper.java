package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleDto scheduleToScheduleDto(Schedule schedule);

    Schedule scheduleDtoToSchedule(ScheduleDto scheduleDto);

    List<ScheduleDto> scheduleListToScheduleDtoList(List<Schedule> schedules);
}
