package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ScheduleDto {

    private long id;

    private Date scheduleDate;

    private Set<LessonDto> lessonsList;

    private List<LessonDto> lessonDtoList;

    public ScheduleDto() {
    }

    public ScheduleDto(long id, Date scheduleDate, Set<LessonDto> lessonsList, List<LessonDto> lessonDtoList) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.lessonsList = lessonsList;
        this.lessonDtoList = lessonDtoList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Set<LessonDto> getLessonsList() {
        return lessonsList;
    }

    public void setLessonsList(Set<LessonDto> lessonsList) {
        this.lessonsList = lessonsList;
    }

    public List<LessonDto> getLessonDtoList() {
        return lessonDtoList;
    }

    public void setLessonDtoList(List<LessonDto> lessonDtoList) {
        this.lessonDtoList = lessonDtoList;
    }
}
