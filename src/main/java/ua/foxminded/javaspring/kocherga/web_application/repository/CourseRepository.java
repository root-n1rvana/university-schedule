package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course getCourseById(long courseId);

}
