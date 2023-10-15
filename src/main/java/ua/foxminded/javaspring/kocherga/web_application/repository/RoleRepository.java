package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByRoleName(RoleName roleName);

    Set<Role> findAllByRoleNameIn(Set<RoleName> roleNames);
}
