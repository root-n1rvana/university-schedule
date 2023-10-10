package ua.foxminded.javaspring.kocherga.web_application.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group getGroupById(long groupId);

    boolean existsByName(String groupName);

    @Transactional
    void deleteByName(String groupName);

    List<Group> findByNameNotIn(List<String> names);
}
