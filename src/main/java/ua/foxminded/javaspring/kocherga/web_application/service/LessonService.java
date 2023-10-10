package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;

import java.util.List;

public interface LessonService {

    List<LessonDto> getAllLessons();

    @Transactional
    void saveNewLesson(LessonDto lessonDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void updateLesson(LessonDto lessonDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void deleteLesson(Long id, RedirectAttributes redirectAttributes);
}
