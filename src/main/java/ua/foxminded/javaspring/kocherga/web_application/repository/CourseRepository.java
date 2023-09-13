package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course getCourseById(long courseId);

    Course getCourseByCourseName(String courseName);

    boolean existsByCourseName(String courseName);

    boolean existsById(Long courseId);

    void deleteByCourseName(String courseName);

    void deleteById(Long courseId);
}
