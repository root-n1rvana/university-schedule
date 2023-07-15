package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule getScheduleById(long scheduleId) {
        return scheduleRepository.getSchedulesById(scheduleId);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
