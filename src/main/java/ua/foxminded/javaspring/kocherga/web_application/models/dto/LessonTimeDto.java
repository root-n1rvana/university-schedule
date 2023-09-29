package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import ua.foxminded.javaspring.kocherga.web_application.models.Lesson;

import java.util.Set;

public class LessonTimeDto {

    private String id;
    private String lessonTime;
    private Set<Lesson> lessonsList;

    public LessonTimeDto() {
    }

    public LessonTimeDto(String id, String lessonTime, Set<Lesson> lessonsList) {
        this.id = id;
        this.lessonTime = lessonTime;
        this.lessonsList = lessonsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Set<Lesson> getLessonsList() {
        return lessonsList;
    }

    public void setLessonsList(Set<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }
}
