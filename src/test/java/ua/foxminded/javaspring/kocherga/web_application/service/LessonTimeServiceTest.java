package ua.foxminded.javaspring.kocherga.web_application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LessonTimeServiceTest {

    @Autowired
    private LessonTimeService lessonTimeService;

    @Test
    public void getLessonTimeById() {
        // Given
        String expectedLessonTime = "8:00-9:30";
        // When
        String actualLessonTime = lessonTimeService.getLessonTimeById(1).getLessonTime();
        // Then
        Assertions.assertThat(actualLessonTime).isEqualTo(expectedLessonTime);
    }
}
