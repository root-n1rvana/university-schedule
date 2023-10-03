package ua.foxminded.javaspring.kocherga.web_application.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course ownerCourse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", nullable = false)
    private Room ownerRoom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    private Group ownerGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule ownerSchedule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_time_id", nullable = false)
    private LessonTime ownerLessonTime;

    public Lesson() {
    }

    public Lesson(long id, Course ownerCourse, Room ownerRoom, Group ownerGroup, Schedule ownerSchedule, LessonTime ownerLessonTime) {
        this.id = id;
        this.ownerCourse = ownerCourse;
        this.ownerRoom = ownerRoom;
        this.ownerGroup = ownerGroup;
        this.ownerSchedule = ownerSchedule;
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

    public LessonTime getOwnerLessonTime() {
        return ownerLessonTime;
    }

    public void setOwnerLessonTime(LessonTime ownerLessonTime) {
        this.ownerLessonTime = ownerLessonTime;
    }
}
