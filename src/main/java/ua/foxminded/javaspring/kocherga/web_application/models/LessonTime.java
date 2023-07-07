package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lessons_time")
public class LessonTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "lesson_time", unique = true, nullable = false)
    private String lessonTime;

    @OneToMany(mappedBy = "ownerLessonTime")
    private Set<Lesson> lessonsList;

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

    public Set<Lesson> getLessonsList() {
        return lessonsList;
    }

    public void setLessonsList(Set<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonTime that = (LessonTime) o;
        return lessonTime.equals(that.lessonTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonTime);
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
