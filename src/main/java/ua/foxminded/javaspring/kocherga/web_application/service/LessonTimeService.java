package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.LessonTime;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonTimeDto;

import java.util.List;

public interface LessonTimeService {

    List<LessonTimeDto> getAllLessonsTime();
}
