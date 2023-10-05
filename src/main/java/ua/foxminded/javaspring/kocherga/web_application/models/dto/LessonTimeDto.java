package ua.foxminded.javaspring.kocherga.web_application.models.dto;

public class LessonTimeDto {

    private long id;
    private String lessonTime;

    public LessonTimeDto() {
    }

    public LessonTimeDto(long id, String lessonTime) {
        this.id = id;
        this.lessonTime = lessonTime;
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
}
