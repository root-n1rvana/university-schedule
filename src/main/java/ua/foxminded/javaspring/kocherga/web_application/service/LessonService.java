package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.repository.LessonRepository;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson getLessonById(long lessonId) {
        return lessonRepository.getLessonById(lessonId);
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

}
