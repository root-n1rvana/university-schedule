package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.LessonMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.*;
import ua.foxminded.javaspring.kocherga.web_application.service.LessonService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private static final String ERROR_MSG = "errorMessage";
    private static final String SUCCESS_MSG = "successMessage";

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final RoomRepository roomRepository;
    private final GroupRepository groupRepository;
    private final LessonTimeRepository lessonTimeRepository;
    private final ScheduleRepository scheduleRepository;
    private final LessonMapper lessonMapper;

    public LessonServiceImpl(LessonRepository lessonRepository,
                             CourseRepository courseRepository,
                             RoomRepository roomRepository,
                             GroupRepository groupRepository,
                             LessonTimeRepository lessonTimeRepository,
                             ScheduleRepository scheduleRepository,
                             LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.roomRepository = roomRepository;
        this.groupRepository = groupRepository;
        this.lessonTimeRepository = lessonTimeRepository;
        this.scheduleRepository = scheduleRepository;
        this.lessonMapper = lessonMapper;

    }

    @Override
    public List<LessonDto> getAllLessons() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::lessonToLessonDto)
                .toList();
    }

    @Override
    public boolean existsByCourseNameRoomLabelGroupNameLessonTime(String courseName, String roomLabel, String groupName, String lessonTime) {
        return lessonRepository.existsByOwnerCourseContainsAndOwnerRoomContainsAndOwnerGroupContainsAndOwnerLessonTimeContains(courseName, roomLabel, groupName, lessonTime);
    }

    @Transactional
    public void save(LessonDto lessonDto) {
        lessonRepository.save(lessonMapper.lessonDtoToLesson(lessonDto));
    }

//    @Transactional
//    @Override
//    public RedirectAttributesDto saveAndGetRedirAttr(LessonDto lessonDto, BindingResult bindingResult) {
//        RedirectAttributesDto redirectAttributesDto = checkErrorsAndHandle(bindingResult);
//        if (redirectAttributesDto.getValue() == null) {
//            Lesson newLesson = new Lesson();
//            Course course = courseRepository.getCourseById(lessonDto.getOwnerCourse().getId());
//            newLesson.setOwnerCourse(course);
//
//            Room room = roomRepository.getRoomById(lessonDto.getOwnerRoom().getId());
//            newLesson.setOwnerRoom(room);
//
//            Group group = groupRepository.getGroupById(lessonDto.getOwnerGroup().getId());
//            newLesson.setOwnerGroup(group);
//
//            LessonTime lessonTime = lessonTimeRepository.findById(lessonDto.getOwnerLessonTime().getId());
//            newLesson.setOwnerLessonTime(lessonTime);
//
//            Schedule schedule = addScheduleIfNotExist(lessonDto.getNewScheduleDate());
//            newLesson.setOwnerSchedule(schedule);
//
//            lessonRepository.save(newLesson);
//            redirectAttributesDto.setName("successMessage");
//            redirectAttributesDto.setValue("Lesson added successfully!");
//        }
//        return redirectAttributesDto;
//    }

    @Transactional
    @Override
    public RedirectAttributesDto saveAndGetRedirAttr(LessonDto lessonDto, BindingResult bindingResult) {
        RedirectAttributesDto redirectAttributesDto = checkErrorsAndHandle(bindingResult);
        if (redirectAttributesDto.getValue() == null) {
            Lesson newLesson = new Lesson();
            updateLessonFromDto(lessonDto, newLesson);
            lessonRepository.save(newLesson);
            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Lesson added successfully!");
        }
        return redirectAttributesDto;
    }

    private RedirectAttributesDto checkErrorsAndHandle(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue("Incorrect data format or the number of characters is out of range");
            return redirectAttributesDto;
        }
        return new RedirectAttributesDto();
    }

    private Lesson updateLessonFromDto(LessonDto lessonDto, Lesson lesson) {
        lesson.setOwnerCourse(courseRepository.getCourseById(lessonDto.getOwnerCourse().getId()));
        lesson.setOwnerRoom(roomRepository.getRoomById(lessonDto.getOwnerRoom().getId()));
        lesson.setOwnerGroup(groupRepository.getGroupById(lessonDto.getOwnerGroup().getId()));
        lesson.setOwnerLessonTime(lessonTimeRepository.findById(lessonDto.getOwnerLessonTime().getId()));
        lesson.setOwnerSchedule(addScheduleIfNotExist(lessonDto.getNewScheduleDate()));
        return lesson;
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
    public RedirectAttributesDto updateLessonAndGetRedirAttr(LessonDto lessonDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        Lesson lessonToEdit = lessonRepository.getLessonById(lessonDto.getId());
        updateLessonFromDto(lessonDto, lessonToEdit);
        lessonRepository.save(lessonToEdit);
        redirectAttributesDto.setName(SUCCESS_MSG);
        redirectAttributesDto.setValue("Lesson updated successfully!");
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto deleteLessonAndGetRedirAttr(Long id) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Lesson deleted successfully!");
        } else {
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue("Lesson not found or could not be deleted");
        }
        return redirectAttributesDto;
    }
}
