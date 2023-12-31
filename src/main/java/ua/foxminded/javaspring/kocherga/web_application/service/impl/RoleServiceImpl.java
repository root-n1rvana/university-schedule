package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RoleDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.RoleMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.RoleService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::roleToRoleDto)
                .toList();
    }

    public Set<Role> getRolesByIds(Collection<Long> ids) {
        List<Role> rolesList = roleRepository.findAllById(ids);
        return new HashSet<>(rolesList);
    }
}
