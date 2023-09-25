package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.CourseMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.CourseService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDto getCourseById(long courseId) {
        Course course = courseRepository.getCourseById(courseId);
        return courseMapper.courseToCourseDto(course);
    }

    @Override
    public CourseDto findByCourseName(String courseName) {
        Course course = courseRepository.getCourseByCourseName(courseName);
        return courseMapper.courseToCourseDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Course::getId))
                .collect(Collectors.toList());
        return courseMapper.courseListToCourseDtoList(courses);
    }

    @Transactional
    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    @Override
    public void save(CourseDto courseDto) {
        Course course = courseMapper.courseDtoToCourse(courseDto);
        courseRepository.save(course);
    }

    @Transactional
    @Override
    public RedirectAttributesDto saveAndGetRedirAttr(CourseDto courseDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (courseRepository.existsByCourseName(courseDto.getCourseName())) {
            redirectAttributesDto.setName("errorMessage");
            redirectAttributesDto.setValue("Course with the same name already exists.");
        } else {
            Course newCourse = new Course();
            newCourse.setCourseName(courseDto.getCourseName());
            newCourse.setCourseDescription(courseDto.getCourseDescription());
            save(newCourse);
            redirectAttributesDto.setName("successMessage");
            redirectAttributesDto.setValue("Course added successfully!");
        }
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto updateAndGetRedirAttr(CourseDto courseDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (courseRepository.existsByCourseName(courseDto.getCourseName())) {
            redirectAttributesDto.setName("errorMessage");
            redirectAttributesDto.setValue("Course with the same name already exists.");
        } else {
            Course courseToUpdate = courseRepository.getCourseById(courseDto.getId());
            courseToUpdate.setCourseName(courseDto.getCourseName());
            courseToUpdate.setCourseDescription(courseDto.getCourseDescription());
            save(courseToUpdate);
            redirectAttributesDto.setName("successMessage");
            redirectAttributesDto.setValue("Course updated successfully!");
        }
        return redirectAttributesDto;
    }

    @Override
    public boolean existsByCourseName(String courseName) {
        return courseRepository.existsByCourseName(courseName);
    }

    @Override
    public boolean existByCourseId(Long courseId) {
        return courseRepository.existsById(courseId);
    }

    @Transactional
    @Override
    public RedirectAttributesDto deleteAndGetRedirAttr(Long courseId) {
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
