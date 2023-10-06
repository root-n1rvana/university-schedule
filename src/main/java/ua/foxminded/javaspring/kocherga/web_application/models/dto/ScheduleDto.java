package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ScheduleDto {

    private long id;

    private LocalDate scheduleDate;

    private Set<LessonDto> lessons;

    private List<LessonDto> lessonsList;

    public ScheduleDto() {
    }

    public ScheduleDto(long id, LocalDate scheduleDate, Set<LessonDto> lessons, List<LessonDto> lessonsList) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.lessons = lessons;
        this.lessonsList = lessonsList;
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

    public Set<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonDto> lessons) {
        this.lessons = lessons;
    }

    public List<LessonDto> getLessonsList() {
        return lessonsList;
    }

    public void setLessonsList(List<LessonDto> lessonsList) {
        this.lessonsList = lessonsList;
    }
}
