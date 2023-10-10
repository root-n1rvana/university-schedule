package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDto lessonToLessonDto(Lesson lesson);

    Lesson lessonDtoToLesson(LessonDto lessonDto);
}
