package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.ScheduleMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.ScheduleService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

    private List<ScheduleDto> sortScheduleList(List<ScheduleDto> schedules) {
        for (ScheduleDto schedule : schedules) {
            List<LessonDto> lessons = new ArrayList<>(schedule.getLessons());
            lessons.sort((lesson1, lesson2) -> {
                int groupComparison = Long.compare(
                        lesson1.getOwnerGroup().getId(),
                        lesson2.getOwnerGroup().getId()
                );
                if (groupComparison == 0) {
                    return Long.compare(
                            lesson1.getOwnerLessonTime().getId(),
                            lesson2.getOwnerLessonTime().getId()
                    );
                }
                return groupComparison;
            });
            schedule.setLessonsList(lessons);
        }
        return schedules;
    }
}
