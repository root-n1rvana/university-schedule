package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.LessonDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.LessonMapper;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.LessonTimeMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.*;
import ua.foxminded.javaspring.kocherga.web_application.service.LessonService;

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
    private final LessonTimeMapper lessonTimeMapper;

    public LessonServiceImpl(LessonRepository lessonRepository,
                             CourseRepository courseRepository,
                             RoomRepository roomRepository,
                             GroupRepository groupRepository,
                             LessonTimeRepository lessonTimeRepository,
                             ScheduleRepository scheduleRepository,
                             LessonMapper lessonMapper,
                             LessonTimeMapper lessonTimeMapper) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.roomRepository = roomRepository;
        this.groupRepository = groupRepository;
        this.lessonTimeRepository = lessonTimeRepository;
        this.scheduleRepository = scheduleRepository;
        this.lessonMapper = lessonMapper;
        this.lessonTimeMapper = lessonTimeMapper;

    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public boolean existsByCourseNameRoomLabelGroupNameLessonTime(String courseName, String roomLabel, String groupName, String lessonTime) {
        return lessonRepository.existsByOwnerCourseContainsAndOwnerRoomContainsAndOwnerGroupContainsAndOwnerLessonTimeContains(courseName, roomLabel, groupName, lessonTime);
    }

    @Transactional
    public void save(LessonDto lessonDto) {
        lessonRepository.save(lessonMapper.lessonDtoToLesson(lessonDto));
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
    public RedirectAttributesDto saveAndGetRedirAttr(LessonDto lessonDto, BindingResult bindingResult) {
        RedirectAttributesDto redirectAttributesDto = checkErrorsAndHandle(bindingResult);
        if (redirectAttributesDto.getValue() == null) {
            Lesson newLesson = new Lesson();
            Course course = courseRepository.getCourseById(lessonDto.getOwnerCourse().getId());
            newLesson.setOwnerCourse(course);

            Room room = roomRepository.getRoomById(lessonDto.getOwnerRoom().getId());
            newLesson.setOwnerRoom(room);

            Group group = groupRepository.getGroupById(lessonDto.getOwnerGroup().getId());
            newLesson.setOwnerGroup(group);

            LessonTime lessonTime = lessonTimeRepository.findById(lessonDto.getOwnerLessonTime().getId());
            newLesson.setOwnerLessonTime(lessonTime);

            Schedule schedule = addScheduleIfNotExist(lessonDto.getNewScheduleDate());
            newLesson.setOwnerSchedule(schedule);

            lessonRepository.save(newLesson);
            redirectAttributesDto.setName("successMessage");
            redirectAttributesDto.setValue("Lesson added successfully!");
        }
        return redirectAttributesDto;
    }

    private RedirectAttributesDto checkErrorsAndHandle(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
            redirectAttributesDto.setName("errorMessage");
            redirectAttributesDto.setValue("Incorrect data format or the number of characters is out of range");
            return redirectAttributesDto;
        }
        return new RedirectAttributesDto();
    }

}
