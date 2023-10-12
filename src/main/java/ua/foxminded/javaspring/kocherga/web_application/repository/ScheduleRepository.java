package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule getByScheduleDate(LocalDate scheduleDate);

    List<Schedule> findAllByScheduleDateBetween(LocalDate startDate, LocalDate endDate);

    boolean existsByScheduleDate(LocalDate scheduleDate);

    @Query("SELECT s FROM Schedule s " +
            "JOIN s.lessons l " +
            "WHERE l.ownerGroup.id = :groupId " +
            "AND s.scheduleDate BETWEEN :startDate AND :endDate " +
            "ORDER BY s.scheduleDate, l.ownerLessonTime.id")
    List<Schedule> findScheduleInDateRangeForGroup(Long groupId, LocalDate startDate, LocalDate endDate);
}
