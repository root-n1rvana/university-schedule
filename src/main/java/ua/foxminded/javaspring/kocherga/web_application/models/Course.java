package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 20, nullable = false)
    private String courseName;

    @Column(name = "description", length = 100)
    private String courseDescription;

    @OneToMany(mappedBy = "ownerCourse")
    private List<Lesson> lessonsList = new ArrayList<>();

    @ManyToMany(mappedBy = "userCourses")
    private List<User> userCourseList = new ArrayList<>();

    public Course() {
    }

    public Course(int id, String courseName, String courseDescription) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public List<Lesson> getLessonsList() {
        return lessonsList;
    }

    public void setLessonsList(List<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }

    public List<User> getUserCourseList() {
        return userCourseList;
    }

    public void setUserCourseList(List<User> userCourseList) {
        this.userCourseList = userCourseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Objects.equals(courseName, course.courseName) && Objects.equals(courseDescription, course.courseDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, courseDescription);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", lessonsList=" + lessonsList +
                ", userCourseList=" + userCourseList +
                '}';
    }
}
