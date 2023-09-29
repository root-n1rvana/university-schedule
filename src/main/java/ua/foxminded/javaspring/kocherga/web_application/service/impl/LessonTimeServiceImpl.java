package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.LessonTime;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonTimeDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.LessonTimeMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.LessonTimeRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.LessonTimeService;

import java.util.List;

@Service
public class LessonTimeServiceImpl implements LessonTimeService {

    private final LessonTimeRepository lessonTimeRepository;
    private final LessonTimeMapper lessonTimeMapper;

    public LessonTimeServiceImpl(LessonTimeRepository lessonTimeRepository, LessonTimeMapper lessonTimeMapper) {
        this.lessonTimeRepository = lessonTimeRepository;
        this.lessonTimeMapper = lessonTimeMapper;
    }

    @Override
    public List<LessonTime> getAllLessonsTime() {
        return lessonTimeRepository.findAll();
    }

    public List<LessonTimeDto> getAllLessonsTimeDto() {
        return lessonTimeMapper.lessonTimeListToLessonTimeDtoList(lessonTimeRepository.findAll());
    }
}
