package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.javaspring.kocherga.web_application.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
