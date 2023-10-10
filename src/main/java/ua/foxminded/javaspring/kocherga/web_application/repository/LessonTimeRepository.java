package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.LessonTime;

@Repository
public interface LessonTimeRepository extends JpaRepository<LessonTime, Long> {

    LessonTime findById(long lessonTimeId);
}
