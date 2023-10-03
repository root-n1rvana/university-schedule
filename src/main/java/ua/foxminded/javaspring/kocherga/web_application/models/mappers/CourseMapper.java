package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto courseToCourseDto(Course course);

    Course courseDtoToCourse(CourseDto courseDto);

    List<CourseDto> courseListToCourseDtoList(List<Course> courses);

    List<Course> courseDtoListToCourseList(List<CourseDto> courses);

    Set<CourseDto> courseSetToCourseDtoSet(Set<Course> courses);

    Set<Course> courseDtoSetToCourseSet(Set<CourseDto> courses);
}
