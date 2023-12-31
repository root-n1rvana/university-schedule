package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lessons_time")
public class LessonTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lesson_time", unique = true, nullable = false)
    private String lessonTime;

    public LessonTime() {
    }

    public LessonTime(long id, String lessonTime) {
        this.id = id;
        this.lessonTime = lessonTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return lessonTime.equals(that.lessonTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonTime);
    }
}
