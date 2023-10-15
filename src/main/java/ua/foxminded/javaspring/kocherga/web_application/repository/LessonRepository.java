package ua.foxminded.javaspring.kocherga.web_application.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;

import java.time.LocalDate;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Lesson getLessonById(long lessonId);

    boolean existsByOwnerGroupIdAndOwnerLessonTimeIdAndOwnerScheduleScheduleDate(long groupId, long lessonTimeId, LocalDate scheduleDate);

    @Transactional
    void deleteByOwnerGroupIdAndOwnerLessonTimeIdAndOwnerScheduleScheduleDate(long groupId, long lessonTimeId, LocalDate scheduleDate);

    Page<Lesson> findAllBy(Pageable pageable);
}
