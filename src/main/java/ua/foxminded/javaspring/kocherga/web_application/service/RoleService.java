package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(long roleId) {
        return roleRepository.getRoleById(roleId);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Set<Role> getRolesByIds(Collection<Long> ids) {
        List<Role> rolesList = roleRepository.findAllById(ids);
        return new HashSet<>(rolesList);
    }
}
