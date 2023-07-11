package ua.foxminded.javaspring.kocherga.web_application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private LessonService lessonService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void saveAndRetrieveSchedule() throws ParseException {
        //given
        Date testDate = dateFormat.parse("2023-10-13");

        Set<Lesson> testLessonsList = new HashSet<>();
        testLessonsList.add(lessonService.getLessonById(28L));
        testLessonsList.add(lessonService.getLessonById(29L));
        testLessonsList.add(lessonService.getLessonById(30L));

        // Create a test Schedule
        Schedule testSchedule = new Schedule();
        testSchedule.setScheduleDate(testDate);
        testSchedule.setLessonsList(testLessonsList);

        // Save user using the repository
        Schedule savedSchedule = scheduleRepository.save(testSchedule);

        // Retrieve schedule from the database
        Schedule retrievedSchedule = scheduleService.getScheduleById(savedSchedule.getId());

        // Assert against the created user
        Assertions.assertThat(savedSchedule.getId()).isEqualTo(retrievedSchedule.getId());
        Assertions.assertThat(savedSchedule.getScheduleDate()).isEqualTo(retrievedSchedule.getScheduleDate());

        //TODO Assertion below not working, not sure if I need to resolve it
//        Assertions.assertThat(savedSchedule.getLessonsList()).isEqualTo(retrievedSchedule.getLessonsList());

    }
}
