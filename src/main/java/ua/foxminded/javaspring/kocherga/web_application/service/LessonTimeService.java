package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.LessonTime;
import ua.foxminded.javaspring.kocherga.web_application.repository.LessonTimeRepository;

import java.util.List;

@Service
public class LessonTimeService {

    private final LessonTimeRepository lessonTimeRepository;

    @Autowired
    public LessonTimeService(LessonTimeRepository lessonTimeRepository) {
        this.lessonTimeRepository = lessonTimeRepository;
    }

    public LessonTime getLessonTimeById(long lessonTimeId) {
        return lessonTimeRepository.findById(lessonTimeId);
    }

    public List<LessonTime> getAllLessonsTime() {
        return lessonTimeRepository.findAll();
    }
}
