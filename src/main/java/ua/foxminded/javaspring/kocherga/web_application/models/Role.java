package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import ua.foxminded.javaspring.kocherga.web_application.models.converter.RoleNameConverter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Type(value = RoleNameConverter.class,
            parameters = {
                    @Parameter(name = "enumClass", value = "ua.foxminded.javaspring.kocherga.web_application.models.RoleName")
            })
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "role_name")
    private RoleName roleName;

    public Role() {
    }

    public Role(long id, RoleName roleName) {
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
        Role role = (Role) o;
        return roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}
