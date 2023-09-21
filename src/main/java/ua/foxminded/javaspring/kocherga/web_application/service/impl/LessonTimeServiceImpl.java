package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.LessonTime;
import ua.foxminded.javaspring.kocherga.web_application.repository.LessonTimeRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.LessonTimeService;

import java.util.List;

@Service
public class LessonTimeServiceImpl implements LessonTimeService {

    private final LessonTimeRepository lessonTimeRepository;

    public LessonTimeServiceImpl(LessonTimeRepository lessonTimeRepository) {
        this.lessonTimeRepository = lessonTimeRepository;
    }

    @Override
    public List<LessonTime> getAllLessonsTime() {
        return lessonTimeRepository.findAll();
    }
}
