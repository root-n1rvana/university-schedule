package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date", unique = true, nullable = false)
    private LocalDate scheduleDate;

    @OneToMany(mappedBy = "ownerSchedule", fetch = FetchType.EAGER)
    private Set<Lesson> lessons;

    public Schedule() {
    }

    public Schedule(long id, LocalDate scheduleDate) {
        this.id = id;
        this.scheduleDate = scheduleDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id && scheduleDate.equals(schedule.scheduleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scheduleDate);
    }
}
