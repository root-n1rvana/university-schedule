package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LessonDto {

    private long id;

    private CourseDto ownerCourse;

    private RoomDto ownerRoom;

    private GroupDto ownerGroup;

    @NotNull
    @NotBlank(message = "Date can't be empty")
    private String newScheduleDate;

    private LessonTimeDto ownerLessonTime;

    private UserDto professor;

    public LessonDto() {
    }

    public LessonDto(long id, CourseDto ownerCourse, RoomDto ownerRoom, GroupDto ownerGroup, String newScheduleDate, LessonTimeDto ownerLessonTime, UserDto professor) {
        this.id = id;
        this.ownerCourse = ownerCourse;
        this.ownerRoom = ownerRoom;
        this.ownerGroup = ownerGroup;
        this.newScheduleDate = newScheduleDate;
        this.ownerLessonTime = ownerLessonTime;
        this.professor = professor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CourseDto getOwnerCourse() {
        return ownerCourse;
    }

    public void setOwnerCourse(CourseDto ownerCourse) {
        this.ownerCourse = ownerCourse;
    }

    public RoomDto getOwnerRoom() {
        return ownerRoom;
    }

    public void setOwnerRoom(RoomDto ownerRoom) {
        this.ownerRoom = ownerRoom;
    }

    public GroupDto getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(GroupDto ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public String getNewScheduleDate() {
        return newScheduleDate;
    }

    public void setNewScheduleDate(String newScheduleDate) {
        this.newScheduleDate = newScheduleDate;
    }

    public LessonTimeDto getOwnerLessonTime() {
        return ownerLessonTime;
    }

    public void setOwnerLessonTime(LessonTimeDto ownerLessonTime) {
        this.ownerLessonTime = ownerLessonTime;
    }

    public UserDto getProfessor() {
        return professor;
    }

    public void setProfessor(UserDto professor) {
        this.professor = professor;
    }
}

