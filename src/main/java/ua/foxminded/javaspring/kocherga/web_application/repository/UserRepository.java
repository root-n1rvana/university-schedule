package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByOwnerGroupId(long groupId);

    User getUserById(long userId);

    User findByLogin(String loginName);

    void deleteByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsById(Long id);

    List<User> findAllByRolesIn(List<Role> roles);
}
