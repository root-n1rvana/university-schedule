package ua.foxminded.javaspring.kocherga.web_application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;

@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void getRoleById() {
        //given
        long adminRoleId = 1;
        long studentRoleId = 2;
        long professorRoleId = 3;
        RoleName adminExpected = RoleName.ADMIN;
        RoleName studentExpected = RoleName.STUDENT;
        RoleName professorExpected = RoleName.PROFESSOR;

        //when
        Role adminResult = roleService.getRoleById(adminRoleId);
        Role studentResult = roleService.getRoleById(studentRoleId);
        Role professorResult = roleService.getRoleById(professorRoleId);

        //then
        Assertions.assertThat(adminResult.getRoleName()).isEqualTo(adminExpected);
        Assertions.assertThat(studentResult.getRoleName()).isEqualTo(studentExpected);
        Assertions.assertThat(professorResult.getRoleName()).isEqualTo(professorExpected);
    }
}
