package ua.foxminded.javaspring.kocherga.web_application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.repository.LessonRepository;

@SpringBootTest
class LessonServiceTest {

    private final LessonService lessonService;
    private final LessonRepository lessonRepository;
    private final LessonTimeService lessonTimeService;
    private final CourseService courseService;
    private final GroupService groupService;
    private final RoomService roomService;
    private final ScheduleService scheduleService;

    @Autowired
    public LessonServiceTest(LessonService lessonService, LessonRepository lessonRepository,
                             LessonTimeService lessonTimeService, CourseService courseService,
                             GroupService groupService, RoomService roomService, ScheduleService scheduleService) {
        this.lessonService = lessonService;
        this.lessonRepository = lessonRepository;
        this.lessonTimeService = lessonTimeService;
        this.courseService = courseService;
        this.groupService = groupService;
        this.roomService = roomService;
        this.scheduleService = scheduleService;
    }

    @Test
    void saveAndRetrieveLesson() {

        //given
        LessonTime testLessonTime = lessonTimeService.getLessonTimeById(1L);
        Course testCourse = courseService.getCourseById(1L);
        Group testGroup = groupService.getGroupById(3L);
        Room testRoom = roomService.getRoomById(1L);
        Schedule testSchedule = scheduleService.getScheduleById(1L);

        // Create a test lesson
        Lesson testLesson = new Lesson();
        testLesson.setOwnerCourse(testCourse);
        testLesson.setOwnerRoom(testRoom);
        testLesson.setOwnerGroup(testGroup);
        testLesson.setOwnerSchedule(testSchedule);
        testLesson.setOwnerLessonTime(testLessonTime);

        // Save user using the repository
        Lesson savedLesson = lessonRepository.save(testLesson);

        // Retrieve user from the database
        Lesson retrievedLesson = lessonService.getLessonById(savedLesson.getId());

        // Assert against the created user
        Assertions.assertThat(savedLesson.getId()).isEqualTo(retrievedLesson.getId());
        Assertions.assertThat(savedLesson.getOwnerCourse()).isEqualTo(retrievedLesson.getOwnerCourse());
        Assertions.assertThat(savedLesson.getOwnerRoom()).isEqualTo(retrievedLesson.getOwnerRoom());
        Assertions.assertThat(savedLesson.getOwnerGroup()).isEqualTo(retrievedLesson.getOwnerGroup());
        Assertions.assertThat(savedLesson.getOwnerSchedule()).isEqualTo(retrievedLesson.getOwnerSchedule());
        Assertions.assertThat(savedLesson.getOwnerLessonTime()).isEqualTo(retrievedLesson.getOwnerLessonTime());
    }
}
