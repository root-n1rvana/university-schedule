package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.ScheduleMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.ScheduleService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
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
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<ScheduleDto> getScheduleInDateRange(String yearMonth) {
        if (yearMonth == null) {
            return new ArrayList<>();
        } else {
            YearMonth ym = YearMonth.parse(yearMonth);
            LocalDate startDate = ym.atDay(1);
            LocalDate endDate = ym.atEndOfMonth();
            List<Schedule> schedules = scheduleRepository.findAllByScheduleDateBetween(startDate, endDate);
            return scheduleMapper.scheduleListToScheduleDtoList(schedules);
        }
    }

//    @Override
//    public List<ScheduleDto> getScheduleInDateRange(String yearMonth) {
//        if (yearMonth == null) {
//            return new ArrayList<>();
//        } else {
//            YearMonth ym = YearMonth.parse(yearMonth);
//            LocalDate startDate = ym.atDay(1);
//            LocalDate endDate = ym.atEndOfMonth();
//            List<ScheduleDto> schedules = scheduleMapper.scheduleListToScheduleDtoList(scheduleRepository.findAllByScheduleDateBetween(startDate, endDate));
//
//            for (ScheduleDto schedule : schedules) {
//                List<LessonDto> lessons = new ArrayList<>(schedule.getLessonsList());
//                lessons.sort(Comparator.comparing(lesson -> lesson.getOwnerGroup().getId()));
//                lessons.sort(Comparator.comparing(lesson -> lesson.getOwnerLessonTime().getId()));
//                schedule.setLessonDtoList(lessons);
//            }
//            return schedules;
//        }
//    }
}
