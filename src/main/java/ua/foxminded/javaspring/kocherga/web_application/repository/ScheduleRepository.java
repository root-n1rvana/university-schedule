package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule getScheduleById(long scheduleId);

    Schedule getByScheduleDate(Date scheduleDate);

    Schedule findByScheduleDate(Date scheduleDate);

    List<Schedule> findAllByScheduleDateBetween(Date startDate, Date endDate);

    boolean existsByScheduleDate(Date scheduleDate);
}
