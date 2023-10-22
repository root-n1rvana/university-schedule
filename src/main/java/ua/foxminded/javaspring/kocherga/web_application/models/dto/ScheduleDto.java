package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class ScheduleDto {

    private long id;

    @DateTimeFormat
    private LocalDate scheduleDate;

    private List<LessonDto> lessons;

    private String yearMonth;
    private String yearMonthDay;
    private Long groupId;
    private Long courseId;
    private String professorLogin;

    public ScheduleDto() {
    }

    public ScheduleDto(long id, LocalDate scheduleDate, List<LessonDto> lessons, String yearMonth, String yearMonthDay,
                       Long groupId, Long courseId) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.lessons = lessons;
        this.yearMonth = yearMonth;
        this.yearMonthDay = yearMonthDay;
        this.groupId = groupId;
        this.courseId = courseId;
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

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getYearMonthDay() {
        return yearMonthDay;
    }

    public void setYearMonthDay(String yearMonthDay) {
        this.yearMonthDay = yearMonthDay;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getProfessorLogin() {
        return professorLogin;
    }

    public void setProfessorLogin(String professorLogin) {
        this.professorLogin = professorLogin;
    }
}
