package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule getByScheduleDate(LocalDate scheduleDate);

    List<Schedule> findAllByScheduleDateBetween(LocalDate startDate, LocalDate endDate);

    boolean existsByScheduleDate(LocalDate scheduleDate);
}
