package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;

import java.util.Set;

public class RoleDto {

    private long id;

    private RoleName roleName;

    private Set<UserDto> users;

    public RoleDto() {
    }

    public RoleDto(long id, RoleName roleName, Set<UserDto> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }
}
