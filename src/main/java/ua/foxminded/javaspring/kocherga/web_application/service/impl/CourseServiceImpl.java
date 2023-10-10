package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.CourseMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.BindingResultErrorsHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.CourseService;
import ua.foxminded.javaspring.kocherga.web_application.service.RedirectAttributesMessageHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.CourseValidationException;

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
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Course::getId))
                .collect(Collectors.toList());
        return courseMapper.courseListToCourseDtoList(courses);
    }

    @Transactional
    @Override
    public void saveNewCourse(CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateCourseBindingResultErrors(bindingResult);
        validateExistingCourseName(courseDto);
        Course newCourse = new Course();
        newCourse.setCourseName(courseDto.getCourseName());
        newCourse.setCourseDescription(courseDto.getCourseDescription());
        courseRepository.save(newCourse);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Course saved successfully!");
    }

    private void validateExistingCourseName(CourseDto courseDto) {
        boolean notSameCourseName = true;
        if (!(courseDto.getId() == null)) {
            notSameCourseName = !courseRepository.getCourseById(courseDto.getId()).getCourseName().equals(courseDto.getCourseName());
        }
        if (courseRepository.existsByCourseName(courseDto.getCourseName()) && notSameCourseName) {
            throw new CourseValidationException("Course with the same name already exists.");
        }
    }

    @Transactional
    @Override
    public void updateCourse(CourseDto courseDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateCourseBindingResultErrors(bindingResult);
        validateExistingCourseName(courseDto);
        Course courseToUpdate = courseRepository.getCourseById(courseDto.getId());
        courseToUpdate.setCourseName(courseDto.getCourseName());
        courseToUpdate.setCourseDescription(courseDto.getCourseDescription());
        courseRepository.save(courseToUpdate);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Course updated successfully!");
    }

    @Transactional
    @Override
    public void deleteCourse(Long courseId, RedirectAttributes redirectAttributes) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            attrMsgHandler.setSuccessMessage(redirectAttributes, "Course deleted successfully!");
        } else {
            attrMsgHandler.setErrorMessage(redirectAttributes, "Course not found or could not be deleted");
        }
    }
}
