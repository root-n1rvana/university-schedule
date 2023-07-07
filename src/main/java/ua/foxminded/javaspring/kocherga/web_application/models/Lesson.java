package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

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

    public Lesson(int Id, Course ownerCourse, Room ownerRoom, Group ownerGroup, Schedule ownerSchedule, LessonTime ownerLessonTime) {
        this.Id = Id;
        this.ownerCourse = ownerCourse;
        this.ownerRoom = ownerRoom;
        this.ownerGroup = ownerGroup;
        this.ownerSchedule = ownerSchedule;
        this.ownerLessonTime = ownerLessonTime;
    }

    public long getId() {
        return Id;
    }

    public void setId(long lessonId) {
        this.Id = lessonId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return ownerSchedule.equals(lesson.ownerSchedule) && ownerLessonTime.equals(lesson.ownerLessonTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerSchedule, ownerLessonTime);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + Id +
//                ", ownerCourse=" + ownerCourse +
                ", ownerRoom=" + ownerRoom +
//                ", ownerGroup=" + ownerGroup +
                ", ownerSchedule=" + ownerSchedule +
                ", ownerLessonTime=" + ownerLessonTime +
                '}';
    }
}
