package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.UserListWrapper;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;
import ua.foxminded.javaspring.kocherga.web_application.service.RoleService;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

import java.util.List;

@Controller
public class ManagementController {

    private final UserService userService;
    private final GroupService groupService;
    private final RoleService roleService;

    @Autowired
    public ManagementController(UserService userService, GroupService groupService, RoleService roleService) {
        this.userService = userService;
        this.groupService = groupService;
        this.roleService = roleService;
    }

    @GetMapping("/management")
    public String showManagementPage(Model model) {
        List<User> users = userService.getAllUsers();
        List<Group> groups = groupService.getAllGroups();
        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("users", users);
        model.addAttribute("groups", groups);
        model.addAttribute("roles", roles);
        return "management";
    }

    @PostMapping("/management/save")
    public String saveManagementChanges(@ModelAttribute("usersWrapper") UserListWrapper userListWrapper,
                                        @RequestParam(required = false, name = "updateUser") Long userIdToUpdate) {
        if (userIdToUpdate != null) {
            userListWrapper.getUsers().stream()
                    .filter(user -> user.getId() == userIdToUpdate)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
//                    .ifPresent(userService::save);
        } else {
            userService.saveAll(userListWrapper.getUsers());
        }
        return "redirect:/management";
    }

    @GetMapping("/management/123")
    public String testerok() {

        User user = userService.getUserByUserId(2L);
        Group group = groupService.getGroupById(9L);
        user.setOwnerGroup(group);
        userService.save(user);
        return "redirect:/home";
    }
}
