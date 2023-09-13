package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/user")
public class StudentController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;

    public StudentController(UserService userService,
                             PasswordEncoder passwordEncoder,
                             GroupRepository groupRepository,
                             RoleRepository roleRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/student-management")
    public String getStudentUsers(Model model) {
        List<User> users = userService.getStudentUsers();
        model.addAttribute("users", users);
        return "management/student-management";
    }

    @PostMapping("/addUser")
    public String addGroup(@RequestParam String loginName, String password, String firstName, String lastName, RedirectAttributes redirectAttributes) {
        if (userService.existByLoginName(loginName)) {
            redirectAttributes.addFlashAttribute("errorMessage", "User with the same login already exists.");
        } else {
//            Group newGroup = new Group();
//            newGroup.setName(newGroupName);
//            groupService.save(newGroup);
            User newUser = new User();
            newUser.setLogin(loginName);
            newUser.setFirstname(firstName);
            newUser.setLastname(lastName);
            newUser.setOwnerGroup(groupRepository.getGroupById(9L)); //id 9L - 'No Group'
            newUser.setPassword(passwordEncoder.encode(password));
            Role role = roleRepository.getRoleById(2L);
            newUser.setRoles(new HashSet<>(Collections.singletonList(role)));
        }
        return "redirect:/group/management";
    }
}
