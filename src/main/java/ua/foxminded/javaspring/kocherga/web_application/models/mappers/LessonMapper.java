package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDto lessonToLessonDto(Lesson lesson);
    Lesson lessonDtoToLesson(LessonDto lessonDto);
    List<LessonDto> lessonListToLessonDtoList(List<Lesson> lessons);
    List<Lesson> lessonDtoListToLessonList(List<LessonDto> lessons);
    Set<LessonDto> lessonSetToLessonDtoSet(Set<Lesson> lessons);
    Set<Lesson> lessonDtoSetToLessonSet(Set<LessonDto> lessons);

}
