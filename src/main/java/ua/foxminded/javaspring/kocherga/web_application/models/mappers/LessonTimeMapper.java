package ua.foxminded.javaspring.kocherga.web_application.models.mappers;

import org.mapstruct.Mapper;
import ua.foxminded.javaspring.kocherga.web_application.models.LessonTime;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonTimeDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonTimeMapper {

    LessonTimeDto lessonTimeToLessonTimeDto(LessonTime lessonTime);

    LessonTime lessonTimeDtoToLessonTime(LessonTimeDto lessonTimeDto);

    List<LessonTimeDto> lessonTimeListToLessonTimeDtoList(List<LessonTime> lessonsTime);
}
