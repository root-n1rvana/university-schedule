package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private int lessonId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course ownerCourse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room ownerRoom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group ownerGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id")
    private Schedule ownerSchedule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_time_id")
    private LessonTime ownerLessonTime;

    public Lesson() {
    }

    public Lesson(int lessonId, Course ownerCourse, Room ownerRoom, Group ownerGroup, Schedule ownerSchedule, LessonTime ownerLessonTime) {
        this.lessonId = lessonId;
        this.ownerCourse = ownerCourse;
        this.ownerRoom = ownerRoom;
        this.ownerGroup = ownerGroup;
        this.ownerSchedule = ownerSchedule;
        this.ownerLessonTime = ownerLessonTime;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
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
        return lessonId == lesson.lessonId && Objects.equals(ownerCourse, lesson.ownerCourse) && Objects.equals(ownerRoom, lesson.ownerRoom) && Objects.equals(ownerGroup, lesson.ownerGroup) && Objects.equals(ownerSchedule, lesson.ownerSchedule) && Objects.equals(ownerLessonTime, lesson.ownerLessonTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId, ownerCourse, ownerRoom, ownerGroup, ownerSchedule, ownerLessonTime);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", ownerCourse=" + ownerCourse +
                ", ownerRoom=" + ownerRoom +
                ", ownerGroup=" + ownerGroup +
                ", ownerSchedule=" + ownerSchedule +
                ", ownerLessonTime=" + ownerLessonTime +
                '}';
    }
}
