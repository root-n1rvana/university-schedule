package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import java.util.Set;

public class LessonTimeDto {

    private long id;
    private String lessonTime;
//    private Set<LessonDto> lessonsList;

    public LessonTimeDto() {
    }

    public LessonTimeDto(long id, String lessonTime/*, Set<LessonDto> lessonsList*/) {
        this.id = id;
        this.lessonTime = lessonTime;
//        this.lessonsList = lessonsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

//    public Set<LessonDto> getLessonsList() {
//        return lessonsList;
//    }
//
//    public void setLessonsList(Set<LessonDto> lessonsList) {
//        this.lessonsList = lessonsList;
//    }
}
