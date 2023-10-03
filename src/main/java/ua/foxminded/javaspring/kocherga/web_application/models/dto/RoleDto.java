package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;


public class RoleDto {

    private long id;

    private RoleName roleName;

    public RoleDto() {
    }

    public RoleDto(long id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
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
}
