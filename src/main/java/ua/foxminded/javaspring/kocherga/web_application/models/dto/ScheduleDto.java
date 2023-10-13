package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class ScheduleDto {

    private long id;

    @DateTimeFormat
    private LocalDate scheduleDate;

    private List<LessonDto> lessons;

    public ScheduleDto() {
    }

    public ScheduleDto(long id, LocalDate scheduleDate, List<LessonDto> lessons) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.lessons = lessons;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public List<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDto> lessons) {
        this.lessons = lessons;
    }
}
