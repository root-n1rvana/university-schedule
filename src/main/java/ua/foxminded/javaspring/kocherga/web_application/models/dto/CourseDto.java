package ua.foxminded.javaspring.kocherga.web_application.models.dto;

public class CourseDto {

    private Long id;
    private String courseName;

    private String courseDescription;

    public CourseDto() {
    }

    public CourseDto(String courseName) {
        this.courseName = courseName;
    }

    public CourseDto(Long id, String courseName, String courseDescription) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
