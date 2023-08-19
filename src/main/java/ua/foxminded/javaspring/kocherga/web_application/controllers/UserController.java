package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;
import ua.foxminded.javaspring.kocherga.web_application.service.RoleService;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final GroupService groupService;
    private final RoleService roleService;

    public UserController(UserService userService, GroupService groupService, RoleService roleService) {
        this.userService = userService;
        this.groupService = groupService;
        this.roleService = roleService;
    }

    @GetMapping("/{groupId}")
    public String getUsersByGroupId(@PathVariable int groupId, Model model) {
        List<User> users = userService.getUsersByGroupId(groupId);
        model.addAttribute("users", users);
        return "db/users";
    }

    @GetMapping("/management")
    public String showManagementPage() {
        return "management";
    }

    @GetMapping("/find-by-login")
    public String findUser(@RequestParam("loginName") String loginName, Model model) {
        User user = userService.findUserByLoginName(loginName);
        List<Group> allGroups = groupService.getAllGroups();
        List<Role> allRoles = roleService.getAllRoles();
        List<Long> roleIds = new ArrayList<>();

        model.addAttribute("user", user);
        model.addAttribute("allGroups", allGroups);
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("roleIds", roleIds);

        return "management";
    }

    @PostMapping
    public String updateUser(@RequestParam("userId") long userId, @RequestParam("group") long groupId,
                             @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        User user = userService.getUserByUserId(userId);
        Group group = groupService.getGroupById(groupId);
        Optional.ofNullable(roleIds)
                .map(roleService::getRolesByIds)
                .ifPresent(user::setRoles);
        user.setOwnerGroup(group);

        userService.save(user);
        return "redirect:/user/management";
    }
}
