package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule getSchedulesById(long scheduleId);
}
