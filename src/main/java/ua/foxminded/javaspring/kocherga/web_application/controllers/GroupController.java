package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

//    @GetMapping("/management")
//    public String showManagementPage(Model model) {
//        List<Group> allGroups = groupService.getAllGroups();
//        model.addAttribute("groups", allGroups);
//        return "management/group-management";
//    }

    @GetMapping("/management")
    public String showManagementPage(Model model) {
        List<Group> groups = groupService.getAllGroups();
        // Sort the groups by ID
        groups.sort(Comparator.comparing(Group::getId));
        model.addAttribute("groups", groups);
        return "management/group-management";
    }


    @PostMapping("/update")
    public String updateGroupName(@RequestParam("groupId") long groupId, @RequestParam("newName") String newName) {
        Group group = groupService.getGroupById(groupId);
        group.setName(newName);
        groupService.save(group);
        return "redirect:/group/management";
    }
}
