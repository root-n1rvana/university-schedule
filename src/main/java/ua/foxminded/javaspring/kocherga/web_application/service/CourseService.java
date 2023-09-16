package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.CourseMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CourseDto getCourseById(long courseId) {
        Course course = courseRepository.getCourseById(courseId);
        return courseMapper.courseToCourseDto(course);
    }

    public CourseDto findByCourseName(String courseName) {
        Course course = courseRepository.getCourseByCourseName(courseName);
        return courseMapper.courseToCourseDto(course);
    }

    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Course::getId))
                .collect(Collectors.toList());
        return courseMapper.courseListToCourseDtoList(courses);
    }

    @Transactional
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public void save(CourseDto courseDto) {
        Course course = courseMapper.courseDtoToCourse(courseDto);
        courseRepository.save(course);
    }

    @Transactional
    public RedirectAttributesDto saveWithRedirAttr(String newCourseName, String newCourseDescription) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto(newCourseName);
        if (courseRepository.existsByCourseName(newCourseName)) {
            redirectAttributesDto.setName("errorMessage");
            redirectAttributesDto.setValue("Course with the same name already exists.");
        } else {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseName(newCourseName);
            courseDto.setCourseDescription(newCourseDescription);
            save(courseDto);
            redirectAttributesDto.setName("successMessage");
            redirectAttributesDto.setValue("Course added successfully!");
        }
        return redirectAttributesDto;
    }

    public boolean existsByCourseName(String courseName) {
        return courseRepository.existsByCourseName(courseName);
    }

    @Transactional
    public void deleteByCourseName(String courseName) {
        courseRepository.deleteByCourseName(courseName);
    }

    @Transactional
    public RedirectAttributesDto deleteWithRedirAttr(Long courseId) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            redirectAttributesDto.setName("deletionSucceeded");
            redirectAttributesDto.setValue("Course deleted successfully!");
        } else {
            redirectAttributesDto.setName("deletionError");
            redirectAttributesDto.setValue("Course not found or could not be deleted");
        }
        return redirectAttributesDto;
    }
}
