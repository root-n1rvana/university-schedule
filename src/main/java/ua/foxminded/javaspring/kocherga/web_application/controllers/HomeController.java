package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;

@Controller
public class HomeController {

    RoleRepository roleRepository;

    @Autowired
    public HomeController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/123")
    public String testerok() {
        System.out.println(roleRepository.getRoleByRoleName(RoleName.STUDENT));

        return "home";
    }
}
