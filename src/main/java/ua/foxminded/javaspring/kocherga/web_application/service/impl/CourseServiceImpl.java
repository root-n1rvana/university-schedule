package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.CourseMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.BindingResultErrorsHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.CourseService;
import ua.foxminded.javaspring.kocherga.web_application.service.RedirectAttributesMessageHandler;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final RedirectAttributesMessageHandler attrMsgHandler;
    private final BindingResultErrorsHandler bindingResultErrHandler;


    public CourseServiceImpl(CourseRepository courseRepository,
                             CourseMapper courseMapper,
                             RedirectAttributesMessageHandler attrMsgHandler,
                             BindingResultErrorsHandler bindingResultErrHandler) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.attrMsgHandler = attrMsgHandler;
        this.bindingResultErrHandler = bindingResultErrHandler;
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
    public void saveAndGetRedirAttr(CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateBindingResultErrors(bindingResult);
        validateExistingCourseName(courseDto);
        Course newCourse = new Course();
        newCourse.setCourseName(courseDto.getCourseName());
        newCourse.setCourseDescription(courseDto.getCourseDescription());
        courseRepository.save(newCourse);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Course saved successfully!");
    }

    @Transactional
    @Override
    public void updateAndGetRedirAttr(CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateBindingResultErrors(bindingResult);
        validateExistingCourseName(courseDto);
        Course courseToUpdate = courseRepository.getCourseById(courseDto.getId());
        courseToUpdate.setCourseName(courseDto.getCourseName());
        courseToUpdate.setCourseDescription(courseDto.getCourseDescription());
        courseRepository.save(courseToUpdate);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Course updated successfully!");
    }

    private void validateExistingCourseName(CourseDto courseDto) {
        boolean notSameCourseName = true;
        if (!(courseDto.getId() == null)) {
            notSameCourseName = !courseRepository.getCourseById(courseDto.getId()).getCourseName().equals(courseDto.getCourseName());
        }
        if (existsByCourseName(courseDto.getCourseName()) && notSameCourseName) {
            throw new ValidationException("Course with the same name already exists.");
        }
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
            redirectAttributesDto.setName("successMessage");
            redirectAttributesDto.setValue("Course deleted successfully!");
        } else {
            redirectAttributesDto.setName("errorMessage");
            redirectAttributesDto.setValue("Course not found or could not be deleted");
        }
        return redirectAttributesDto;
    }
}
