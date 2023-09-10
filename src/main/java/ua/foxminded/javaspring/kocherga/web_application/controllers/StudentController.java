package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class StudentController {

    private final UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/student-management")
    public String showStudentManagementPage(Model model) {
        // Retrieve a list of users with the STUDENT role using your UserService
        List<User> students = userService.getUsersByRole(RoleName.ROLE_STUDENT);

        // Add the list of students to the model
        model.addAttribute("students", students);

        return "management/student-management";
    }
}
