package ua.foxminded.javaspring.kocherga.web_application.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course getCourseById(long courseId);

    Course getCourseByCourseName(String courseName);

    boolean existsByCourseName(String courseName);

    boolean existsById(@NonNull Long courseId);

    @Transactional
    void deleteByCourseName(String courseName);

    @Transactional
    void deleteById(@NonNull Long courseId);

    List<Course> findAllByIdIn(List<Long> coursesIds);
}
