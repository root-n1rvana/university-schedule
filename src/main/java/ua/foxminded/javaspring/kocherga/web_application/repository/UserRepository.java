package ua.foxminded.javaspring.kocherga.web_application.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(long userId);

    User findByLogin(String loginName);

    @Transactional
    void deleteByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsById(long id);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.roleName = :roleName")
    Page<User> findByRoleName(RoleName roleName, Pageable pageable);


    Page<User> findAllByOrderByIdAsc(Pageable pageable);
}
