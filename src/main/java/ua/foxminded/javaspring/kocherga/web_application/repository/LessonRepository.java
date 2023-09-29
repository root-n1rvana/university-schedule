package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Lesson getLessonById(long lessonId);

    boolean existsByOwnerCourseContainsAndOwnerRoomContainsAndOwnerGroupContainsAndOwnerLessonTimeContains(String courseName, String roomLabel, String groupName, String lessonTime);
}
