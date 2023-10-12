package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.ScheduleMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.ScheduleService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(scheduleMapper::scheduleToScheduleDto)
                .toList();
    }

    @Override
    public List<ScheduleDto> getScheduleInDateRange(String yearMonth) {
        if (yearMonth == null || yearMonth.isEmpty()) {
            return Collections.emptyList();
        }
        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();
        List<Schedule> schedules = scheduleRepository.findAllByScheduleDateBetween(startDate, endDate);
        return sortScheduleList(scheduleMapper.scheduleListToScheduleDtoList(schedules));
    }

    @Override
    public List<ScheduleDto> getScheduleInDateRangeForGroupQuery(Long groupId, String yearMonth) {
        if (isInvalidInput(yearMonth, groupId)) {
            return Collections.emptyList();
        }
        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();
        return scheduleMapper.scheduleListToScheduleDtoList(scheduleRepository.findScheduleInDateRangeForGroup(groupId, startDate, endDate));
    }

    private List<ScheduleDto> sortScheduleList(List<ScheduleDto> schedules) {
        return schedules.stream()
                .filter(schedule -> !schedule.getLessons().isEmpty())
                .peek(schedule -> schedule.setLessonsList(schedule.getLessons().stream()
                        .sorted(Comparator.comparingLong(lesson -> ((LessonDto) lesson).getOwnerGroup().getId())
                                .thenComparingLong(lesson -> ((LessonDto) lesson).getOwnerLessonTime().getId()))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }


    public List<ScheduleDto> getScheduleInDateRangeForGroup(Long groupId, String yearMonth) {
        if (isInvalidInput(yearMonth, groupId)) {
            return Collections.emptyList();
        }
        List<ScheduleDto> schedulesInRange = getScheduleInDateRange(yearMonth);
        return filterAndSortSchedules(schedulesInRange, groupId);
    }

    private boolean isInvalidInput(String yearMonth, Long groupId) {
        return yearMonth == null || yearMonth.isEmpty() || groupId == null;
    }

    private List<ScheduleDto> filterAndSortSchedules(List<ScheduleDto> schedules, Long groupId) {
        return schedules.stream()
                .filter(scheduleDto -> {
                    List<LessonDto> filteredLessons = scheduleDto.getLessons().stream()
                            .filter(lesson -> Objects.equals(lesson.getOwnerGroup().getId(), groupId))
                            .sorted(Comparator.comparingLong(lesson -> lesson.getOwnerLessonTime().getId()))
                            .collect(Collectors.toList());
                    scheduleDto.setLessonsList(filteredLessons);
                    return !filteredLessons.isEmpty();
                })
                .collect(Collectors.toList());
    }
}
