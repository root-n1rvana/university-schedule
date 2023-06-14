package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.repository.LessonRepository;

import java.util.List;

@Service
public class LessonService {

    private LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

//    @Transactional
//    public List<Lesson> getLessonsByGroupIdAndScheduleId(int groupId, int scheduleId) {
//        return lessonRepository.findByOwnerGroupIdAndOwnerScheduleId(groupId, scheduleId);
//    }


}
