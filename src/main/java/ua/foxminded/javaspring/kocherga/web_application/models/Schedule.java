package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private Date scheduleDate;

    @OneToMany(mappedBy = "ownerSchedule")
    private List<Lesson> lessonsList;

    public Schedule() {
    }

    public Schedule(int id, Date scheduleDate) {
        this.id = id;
        this.scheduleDate = scheduleDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
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
        Schedule schedule = (Schedule) o;
        return id == schedule.id && Objects.equals(scheduleDate, schedule.scheduleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scheduleDate);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", scheduleDate=" + scheduleDate +
                ", lessonsList=" + lessonsList +
                '}';
    }
}
