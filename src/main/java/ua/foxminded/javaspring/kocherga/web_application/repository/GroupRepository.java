package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group getGroupById(long groupId);
}
