package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleDto scheduleToScheduleDto(Schedule schedule);
    Schedule scheduleDtoToSchedule(ScheduleDto scheduleDto);
    List<ScheduleDto> scheduleListToScheduleDtoList(List<Schedule> schedules);
    List<Schedule> scheduleDtoListToScheduleList(List<ScheduleDto> schedules);
    Set<ScheduleDto> scheduleSetToScheduleDtoSet(Set<Schedule> schedules);
    Set<Schedule> scheduleDtoSetToScheduleList(Set<ScheduleDto> schedules);
}
