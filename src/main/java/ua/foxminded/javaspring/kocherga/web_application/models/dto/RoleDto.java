package ua.foxminded.javaspring.kocherga.web_application.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;

import java.util.Objects;

public class RoleDto {

    private long id;

    @NotBlank
    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return id == roleDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
