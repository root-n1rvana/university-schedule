package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lessons_time")
public class LessonTime {

    public enum LessonTimeEnum {
        LESSON_1_TIME("8:00-9:30"),
        LESSON_2_TIME("9:45-11:15"),
        LESSON_3_TIME("11:30-13:00"),
        LESSON_4_TIME("13:30-15:00"),
        LESSON_5_TIME("15:15-16:45");

        private final String value;

        LessonTimeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_time", nullable = false)
    private LessonTimeEnum lessonTime;

    @OneToMany(mappedBy = "ownerLessonTime")
    private List<Lesson> lessonsList;

    public LessonTime() {
    }

    public LessonTime(int id, LessonTimeEnum lessonTime) {
        Id = id;
        this.lessonTime = lessonTime;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public LessonTimeEnum getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(LessonTimeEnum lessonTime) {
        this.lessonTime = lessonTime;
    }

    public List<Lesson> getLessonsList() {
        return lessonsList;
    }

    public void setLessonsList(List<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
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
