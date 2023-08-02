package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleById(long roleId);

}
