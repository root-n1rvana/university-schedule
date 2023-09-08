package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByOwnerGroupId(long groupId);

    List<User> findByRoles(RoleName roleName);

    User getUserById(long userId);

    User findByLogin(String loginName);

//    User findByLogin(String login);
}
