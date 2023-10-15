package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDto lessonToLessonDto(Lesson lesson);

    Lesson lessonDtoToLesson(LessonDto lessonDto);

    default Page<LessonDto> pageLessonToPageLessonDto(Page<Lesson> page) {
        return page.map(this::lessonToLessonDto);
    }
}
