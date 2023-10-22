package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;

import java.time.LocalDate;
import java.util.List;

@Repository //todo refactor methods in repo
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule getByScheduleDate(LocalDate scheduleDate);

    List<Schedule> getScheduleByScheduleDateBetween(LocalDate start, LocalDate end);

    boolean existsByScheduleDate(LocalDate scheduleDate);

    @Query("SELECT s FROM Schedule s " +
            "JOIN FETCH s.lessons l " +
            "WHERE l.ownerGroup.id = :groupId " +
            "AND s.scheduleDate BETWEEN :startDate AND :endDate " +
            "ORDER BY s.scheduleDate, l.ownerLessonTime.id")
    List<Schedule> findScheduleByGroupAndDateBetween(Long groupId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT s FROM Schedule s " +
            "JOIN FETCH s.lessons l " +
            "WHERE s.scheduleDate BETWEEN :startDate AND :endDate " +
            "ORDER BY s.scheduleDate ASC, l.ownerGroup.id ASC, l.ownerLessonTime.id ASC")
    List<Schedule> findScheduleInDateRange(LocalDate startDate, LocalDate endDate);

    @Query("   SELECT s FROM Schedule s " +
            "    JOIN FETCH s.lessons l " +
            "   WHERE l.professor.login = :professorLogin " +
            "     AND s.scheduleDate BETWEEN :startDate AND :endDate " +
            "ORDER BY s.scheduleDate, l.ownerLessonTime.id")
    List<Schedule> findScheduleByProfessorLoginAndDateBetween(String professorLogin, LocalDate startDate, LocalDate endDate);
}
