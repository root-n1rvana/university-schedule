package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;
import ua.foxminded.javaspring.kocherga.web_application.service.RoleService;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

//    @GetMapping("/management")
//    public String showManagementPage(Model model) {
//        List<User> users = userService.getAllUsers();
//        List<Group> groups = groupService.getAllGroups();
//        List<Role> roles = roleService.getAllRoles();
//
//        model.addAttribute("users", users);
//        model.addAttribute("groups", groups);
//        model.addAttribute("roles", roles);
//        return "management";
//    }

//    @PostMapping("/management/save")
//    public String saveManagementChanges(@ModelAttribute("usersWrapper") UserListWrapper userListWrapper,
//                                        @RequestParam(required = false, name = "updateUser") Long userIdToUpdate) {
//        if (userIdToUpdate != null) {
//            userListWrapper.getUsers().stream()
//                    .filter(user -> user.getId() == userIdToUpdate)
//                    .findFirst()
//                    .orElseThrow(RuntimeException::new);
//                    .ifPresent(userService::save);
//        } else {
//            userService.saveAll(userListWrapper.getUsers());
//        }
//        return "redirect:/management";
//    }

    @GetMapping("/management")
    public String showManagementPage() {
        return "management";
    }

    @PostMapping("/management")
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

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("userId") long userId, @RequestParam("group") long groupId,
                             @RequestParam(value = "roles", required = false) Long[] roleIds) {
        User user = userService.getUserByUserId(userId);
        Group group = groupService.getGroupById(groupId);

        List<Long> roleIdsList = roleIds != null ? Arrays.asList(roleIds) : new ArrayList<>();
        Set<Role> roles = roleService.getRolesByIds(roleIdsList);//TODO roleIds=null WTF

        user.setOwnerGroup(group);
        user.setRoles(roles);
        userService.saveUser(user);

        return "redirect:/management";
    }

}
