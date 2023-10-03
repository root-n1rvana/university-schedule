package ua.foxminded.javaspring.kocherga.web_application.models.dto;

public class LessonDto {

    private long id;

    private CourseDto ownerCourse;

    private RoomDto ownerRoom;

    private GroupDto ownerGroup;

//    private ScheduleDto ownerSchedule;

    private String newScheduleDate;

    private LessonTimeDto ownerLessonTime;

    public LessonDto() {
    }

    public LessonDto(long id, CourseDto ownerCourse, RoomDto ownerRoom, GroupDto ownerGroup, /*ScheduleDto ownerSchedule,*/
                     String newScheduleDate, LessonTimeDto ownerLessonTime) {
        this.id = id;
        this.ownerCourse = ownerCourse;
        this.ownerRoom = ownerRoom;
        this.ownerGroup = ownerGroup;
//        this.ownerSchedule = ownerSchedule;
        this.newScheduleDate = newScheduleDate;
        this.ownerLessonTime = ownerLessonTime;
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

//    public ScheduleDto getOwnerSchedule() {
//        return ownerSchedule;
//    }
//
//    public void setOwnerSchedule(ScheduleDto ownerSchedule) {
//        this.ownerSchedule = ownerSchedule;
//    }

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

    @Override
    public String toString() {
        return "LessonDto{" +
                "id=" + id +
                ", ownerCourse=" + ownerCourse +
                ", ownerRoom=" + ownerRoom +
                ", ownerGroup=" + ownerGroup +
//                ", ownerSchedule=" + ownerSchedule +
                ", newScheduleDate='" + newScheduleDate + '\'' +
                ", ownerLessonTime=" + ownerLessonTime +
                '}';
    }
}
