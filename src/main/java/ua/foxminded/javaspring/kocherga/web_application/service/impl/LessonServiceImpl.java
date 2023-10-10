package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.LessonMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.*;
import ua.foxminded.javaspring.kocherga.web_application.service.BindingResultErrorsHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.LessonService;
import ua.foxminded.javaspring.kocherga.web_application.service.RedirectAttributesMessageHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.LessonValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final RoomRepository roomRepository;
    private final GroupRepository groupRepository;
    private final LessonTimeRepository lessonTimeRepository;
    private final ScheduleRepository scheduleRepository;
    private final LessonMapper lessonMapper;
    private final RedirectAttributesMessageHandler attrMsgHandler;
    private final BindingResultErrorsHandler bindingResultErrHandler;

    public LessonServiceImpl(LessonRepository lessonRepository,
                             CourseRepository courseRepository,
                             RoomRepository roomRepository,
                             GroupRepository groupRepository,
                             LessonTimeRepository lessonTimeRepository,
                             ScheduleRepository scheduleRepository,
                             LessonMapper lessonMapper,
                             RedirectAttributesMessageHandler attrMsgHandler,
                             BindingResultErrorsHandler bindingResultErrHandler) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.roomRepository = roomRepository;
        this.groupRepository = groupRepository;
        this.lessonTimeRepository = lessonTimeRepository;
        this.scheduleRepository = scheduleRepository;
        this.lessonMapper = lessonMapper;
        this.attrMsgHandler = attrMsgHandler;
        this.bindingResultErrHandler = bindingResultErrHandler;
    }

    @Override
    public List<LessonDto> getAllLessons() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::lessonToLessonDto)
                .toList();
    }

    @Transactional
    @Override
    public void saveNewLesson(LessonDto lessonDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateLessonBindingResultErrors(bindingResult);
        checkLessonExist(lessonDto);
        Lesson newLesson = new Lesson();
        fillLessonByLessonDto(lessonDto, newLesson);
        lessonRepository.save(newLesson);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Lesson added successfully!");
    }

    private void checkLessonExist(LessonDto lessonDto) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(lessonDto.getNewScheduleDate(), dateFormatter);
        if (lessonRepository.existsByOwnerGroupIdAndOwnerLessonTimeIdAndOwnerScheduleScheduleDate(lessonDto.getOwnerGroup().getId(), lessonDto.getOwnerLessonTime().getId(), date)) {
            throw new LessonValidationException("Lesson for this group with same Date and Time already exists");
        }
    }

    private void fillLessonByLessonDto(LessonDto lessonDto, Lesson lesson) {
        lesson.setOwnerCourse(courseRepository.getCourseById(lessonDto.getOwnerCourse().getId()));
        lesson.setOwnerRoom(roomRepository.getRoomById(lessonDto.getOwnerRoom().getId()));
        lesson.setOwnerGroup(groupRepository.getGroupById(lessonDto.getOwnerGroup().getId()));
        lesson.setOwnerLessonTime(lessonTimeRepository.findById(lessonDto.getOwnerLessonTime().getId()));
        lesson.setOwnerSchedule(addScheduleIfNotExist(lessonDto.getNewScheduleDate()));
    }

    private Schedule addScheduleIfNotExist(String scheduleDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(scheduleDate, dateFormatter);
        if (scheduleRepository.existsByScheduleDate(date)) {
            return scheduleRepository.getByScheduleDate(date);
        } else {
            Schedule newSchedule = new Schedule();
            newSchedule.setScheduleDate(date);
            return scheduleRepository.save(newSchedule);
        }
    }

    @Transactional
    @Override
    public void updateLesson(LessonDto lessonDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateLessonBindingResultErrors(bindingResult);
        checkLessonExist(lessonDto);
        Lesson lessonToEdit = lessonRepository.getLessonById(lessonDto.getId());
        fillLessonByLessonDto(lessonDto, lessonToEdit);
        lessonRepository.save(lessonToEdit);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Lesson updated successfully!");
    }

    @Transactional
    @Override
    public void deleteLesson(Long id, RedirectAttributes redirectAttributes) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            attrMsgHandler.setSuccessMessage(redirectAttributes, "Lesson deleted successfully!");
        } else {
            attrMsgHandler.setErrorMessage(redirectAttributes, "Lesson not found or could not be deleted");
        }
    }
}
