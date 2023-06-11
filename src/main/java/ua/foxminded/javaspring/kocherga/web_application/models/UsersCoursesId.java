package ua.foxminded.javaspring.kocherga.web_application.models;

import java.io.Serializable;

public class UsersCoursesId implements Serializable {

    private int userId;

    private int courseId;

    public UsersCoursesId() {
    }

    public UsersCoursesId(int userId, int courseId) {
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
}
