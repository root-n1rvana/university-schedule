package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_courses")
@IdClass(UsersCoursesId.class)
public class UserCourse {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "course_id")
    private int courseId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User ownerUser;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course ownerCourse;

    public UserCourse() {
    }

    public UserCourse(int userId, int courseId, User ownerUser, Course ownerCourse) {
        this.userId = userId;
        this.courseId = courseId;
        this.ownerUser = ownerUser;
        this.ownerCourse = ownerCourse;
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

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public Course getOwnerCourse() {
        return ownerCourse;
    }

    public void setOwnerCourse(Course ownerCourse) {
        this.ownerCourse = ownerCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCourse that = (UserCourse) o;
        return userId == that.userId && courseId == that.courseId && Objects.equals(ownerUser, that.ownerUser) && Objects.equals(ownerCourse, that.ownerCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId, ownerUser, ownerCourse);
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "userId=" + userId +
                ", courseId=" + courseId +
                ", ownerUser=" + ownerUser +
                ", ownerCourse=" + ownerCourse +
                '}';
    }
}
