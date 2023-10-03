package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserDto {

    private Long id;
    @NotEmpty
    @Size(max = 50)
    private String firstname;

    @NotEmpty
    @Size(max = 50)
    private String lastname;

    @NotEmpty
    @Size(max = 20)
    private String login;

    @NotEmpty(message = "Password should not be empty")
    private String password;
    private GroupDto ownerGroup;
    private Set<RoleDto> roles;
    private Set<CourseDto> userCourses;

    public UserDto() {
    }

    public UserDto(Long id, String firstname, String lastname, String login, String password, GroupDto ownerGroup,
                   Set<RoleDto> roles, Set<CourseDto> userCourses) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.ownerGroup = ownerGroup;
        this.roles = roles;
        this.userCourses = userCourses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GroupDto getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(GroupDto ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public Set<CourseDto> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(Set<CourseDto> userCourses) {
        this.userCourses = userCourses;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", ownerGroup=" + ownerGroup +
                ", roles=" + roles +
                ", userCourses=" + userCourses +
                '}';
    }
}
