package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.ScheduleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getScheduleInDateRange(String yearMonth) {

        if (yearMonth == null) {
            return new ArrayList<>();
        } else {

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM");
            Date parsedDate = null;

            try {
                parsedDate = inputFormat.parse(yearMonth);
            } catch (ParseException e) {
                e.printStackTrace();
                return Collections.emptyList();
            }

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(parsedDate);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date startDate = calendar.getTime();

            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date endDate = calendar.getTime();

            return scheduleRepository.findAllByScheduleDateBetween(startDate, endDate);
        }
    }
}
