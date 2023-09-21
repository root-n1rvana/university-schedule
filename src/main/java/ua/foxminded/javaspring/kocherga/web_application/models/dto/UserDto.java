package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;

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
    private Group ownerGroup;
    private Set<Role> roles;
    private Set<Course> userCourses;

    public UserDto() {
    }

    public UserDto(Long id, String firstname, String lastname,
                   String login, String password,
                   Group ownerGroup, Set<Role> roles, Set<Course> userCourses) {
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

    public Group getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(Group ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public void setOwnerGroupId(Long groupId) {
        if (ownerGroup == null) {
            ownerGroup = new Group();
        }
        ownerGroup.setId(groupId);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Course> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(Set<Course> userCourses) {
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
