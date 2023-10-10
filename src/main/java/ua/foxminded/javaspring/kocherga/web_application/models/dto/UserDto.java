package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.Set;

public class UserDto {

    private Long id;
    @NotEmpty
    @Size(min = 2, max = 50, message = "Firstname should have at least 2 and max 50 characters")
    private String firstname;

    @NotEmpty
    @Size(min = 2, max = 50, message = "Lastname should have at least 2 and max 50 characters")
    private String lastname;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 20, message = "Login should have at least 2 and max 20 characters")
    private String login;

    @NotNull
    @NotEmpty(message = "Password should not be empty")
    private String password;
    private GroupDto ownerGroup;
    private Set<RoleDto> roles;
    private Set<CourseDto> userCourses;
    private Collection<Long> roleIds;

    private String uiPage;

    public UserDto() {
    }

    public UserDto(Long id, String firstname, String lastname, String login, String password, GroupDto ownerGroup,
                   Set<RoleDto> roles, Set<CourseDto> userCourses, Collection<Long> roleIds, String uiPage) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.ownerGroup = ownerGroup;
        this.roles = roles;
        this.userCourses = userCourses;
        this.roleIds = roleIds;
        this.uiPage = uiPage;
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

    public Collection<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Collection<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getUiPage() {
        return uiPage;
    }

    public void setUiPage(String uiPage) {
        this.uiPage = uiPage;
    }
}
