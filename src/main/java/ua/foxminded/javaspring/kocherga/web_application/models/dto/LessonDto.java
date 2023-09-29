package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import ua.foxminded.javaspring.kocherga.web_application.models.*;

public class LessonDto {

    private long id;

    private Course ownerCourse;

    private Room ownerRoom;

    private Group ownerGroup;

    private Schedule ownerSchedule;

    private String newScheduleDate;

    private LessonTime ownerLessonTime;

    public LessonDto() {
    }

    public LessonDto(long id, Course ownerCourse, Room ownerRoom, Group ownerGroup,
                     Schedule ownerSchedule, String newScheduleDate,
                     LessonTime ownerLessonTime) {
        this.id = id;
        this.ownerCourse = ownerCourse;
        this.ownerRoom = ownerRoom;
        this.ownerGroup = ownerGroup;
        this.ownerSchedule = ownerSchedule;
        this.newScheduleDate = newScheduleDate;
        this.ownerLessonTime = ownerLessonTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getOwnerCourse() {
        return ownerCourse;
    }

    public void setOwnerCourse(Course ownerCourse) {
        this.ownerCourse = ownerCourse;
    }

    public Room getOwnerRoom() {
        return ownerRoom;
    }

    public void setOwnerRoom(Room ownerRoom) {
        this.ownerRoom = ownerRoom;
    }

    public Group getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(Group ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public Schedule getOwnerSchedule() {
        return ownerSchedule;
    }

    public void setOwnerSchedule(Schedule ownerSchedule) {
        this.ownerSchedule = ownerSchedule;
    }

    public String getNewScheduleDate() {
        return newScheduleDate;
    }

    public void setNewScheduleDate(String newScheduleDate) {
        this.newScheduleDate = newScheduleDate;
    }

    public LessonTime getOwnerLessonTime() {
        return ownerLessonTime;
    }

    public void setOwnerLessonTime(LessonTime ownerLessonTime) {
        this.ownerLessonTime = ownerLessonTime;
    }

    @Override
    public String toString() {
        return "LessonDto{" +
                "id=" + id +
                ", ownerCourse=" + ownerCourse +
                ", ownerRoom=" + ownerRoom +
                ", ownerGroup=" + ownerGroup +
                ", ownerSchedule=" + ownerSchedule +
                ", newScheduleDate='" + newScheduleDate + '\'' +
                ", ownerLessonTime=" + ownerLessonTime +
                '}';
    }
}
