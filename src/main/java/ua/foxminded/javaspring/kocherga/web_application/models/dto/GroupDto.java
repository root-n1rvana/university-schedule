package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class GroupDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 10, message = "name should have at least 2 and max 20 characters")
    private String name;

    private List<CourseDto> assignedCourses;

    private List<Long> coursesIds;

    public GroupDto() {
    }

    public GroupDto(Long id) {
        this.id = id;
    }

    public GroupDto(Long id, String name, List<CourseDto> assignedCourses, List<Long> coursesIds) {
        this.id = id;
        this.name = name;
        this.assignedCourses = assignedCourses;
        this.coursesIds = coursesIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseDto> getAssignedCourses() {
        return assignedCourses;
    }

    public void setAssignedCourses(List<CourseDto> assignedCourses) {
        this.assignedCourses = assignedCourses;
    }

    public List<Long> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(List<Long> coursesIds) {
        this.coursesIds = coursesIds;
    }
}
