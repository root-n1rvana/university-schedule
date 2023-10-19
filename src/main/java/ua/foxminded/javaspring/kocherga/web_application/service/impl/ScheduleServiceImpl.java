package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.ScheduleMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.ScheduleService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

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
        List<Schedule> schedules = scheduleRepository.findScheduleInDateRange(startDate, endDate);
        return scheduleMapper.scheduleListToScheduleDtoList(schedules);
    }

    @Override
    public List<ScheduleDto> getScheduleInDateRangeForGroup(ScheduleDto scheduleDto) {
        if (isInvalidInput(scheduleDto.getYearMonth(), scheduleDto.getGroupId())) {
            return Collections.emptyList();
        }
        String yearMonth = scheduleDto.getYearMonth();
        Long groupId = scheduleDto.getGroupId();

        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();
        return scheduleMapper.scheduleListToScheduleDtoList(scheduleRepository.findScheduleInDateRangeForGroup(groupId, startDate, endDate));
    }

    @Override
    public List<ScheduleDto> getScheduleForDayForGroup(ScheduleDto scheduleDto) {
        if (isInvalidInput(scheduleDto.getYearMonthDay(), scheduleDto.getGroupId())) {
            return Collections.emptyList();
        }
        String yearMonthDay = scheduleDto.getYearMonthDay();
        Long groupId = scheduleDto.getGroupId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(yearMonthDay, formatter);
        return scheduleMapper.scheduleListToScheduleDtoList(scheduleRepository.findScheduleInDateRangeForGroup(groupId, localDate, localDate));
    }

    @Override
    public List<ScheduleDto> getScheduleInDateRangeForTeacher(ScheduleDto scheduleDto) {
        if (isInvalidInput(scheduleDto.getYearMonth(), scheduleDto.getCourseId())) {
            return Collections.emptyList();
        }
        String yearMonth = scheduleDto.getYearMonth();
        Long courseId = scheduleDto.getCourseId();

        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();
        return scheduleMapper.scheduleListToScheduleDtoList(scheduleRepository.findScheduleInDateRangeForCourse(courseId, startDate, endDate));
    }

    @Override
    public List<ScheduleDto> getScheduleForDayForTeacher(ScheduleDto scheduleDto) {
        if (isInvalidInput(scheduleDto.getYearMonthDay(), scheduleDto.getCourseId())) {
            return Collections.emptyList();
        }
        String yearMonthDay = scheduleDto.getYearMonthDay();
        Long courseId = scheduleDto.getCourseId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(yearMonthDay, formatter);
        return scheduleMapper.scheduleListToScheduleDtoList(scheduleRepository.findScheduleInDateRangeForCourse(courseId, localDate, localDate));
    }

    private boolean isInvalidInput(String yearMonth, Long id) {
        return yearMonth == null || yearMonth.isEmpty() || id == null;
    }
}
