package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAllRoles();
}
