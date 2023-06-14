package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_courses")
public class UserCourse {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "course_id")
    private int courseId;

    public UserCourse() {
    }

    public UserCourse(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCourse that = (UserCourse) o;
        return userId == that.userId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "userId=" + userId +
                ", courseId=" + courseId +
                '}';
    }
}
