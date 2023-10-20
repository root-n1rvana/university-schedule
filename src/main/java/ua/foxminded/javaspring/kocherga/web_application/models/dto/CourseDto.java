package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class CourseDto {

    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20, message = "Course Name should have at least 2 and max 20 characters")
    private String courseName;

    @Size(min = 2, max = 100, message = "Course Description should have at least 2 and max 100 characters")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto courseDto = (CourseDto) o;
        return id.equals(courseDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
