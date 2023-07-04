package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lessons_time")
public class LessonTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "lesson_time", nullable = false)
    private String lessonTime;

    @OneToMany(mappedBy = "ownerLessonTime")
    private List<Lesson> lessonsList;

    public LessonTime() {
    }


    public LessonTime(long id, String lessonTime) {
        Id = id;
        this.lessonTime = lessonTime;
    }

    public long getId() {
        return Id;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonTime that = (LessonTime) o;
        return Id == that.Id && lessonTime == that.lessonTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, lessonTime);
    }

    @Override
    public String toString() {
        return "LessonTime{" +
                "Id=" + Id +
                ", lessonTime=" + lessonTime +
                ", lessonsList=" + lessonsList +
                '}';
    }
}
