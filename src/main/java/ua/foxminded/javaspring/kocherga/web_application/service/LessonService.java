package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.validation.BindingResult;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;

import java.util.List;

public interface LessonService {

    List<LessonDto> getAllLessons();

    boolean existsByCourseNameRoomLabelGroupNameLessonTime(String courseName, String roomLabel, String groupName, String lessonTime);

    @Transactional
    RedirectAttributesDto saveAndGetRedirAttr(LessonDto lessonDto, BindingResult bindingResult);
}
