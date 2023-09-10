package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/management")
    public String showManagementPage(Model model) {
        List<Group> groups = groupService.getAllGroups().stream()
                .sorted(Comparator.comparing(Group::getId))
                .collect(Collectors.toList());
//        groups.sort(Comparator.comparing(Group::getId));
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

    @PostMapping("/addGroup")
    public String addGroup(@RequestParam String newGroupName, RedirectAttributes redirectAttributes) {
        if (groupService.existsByGroupName(newGroupName)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Group with the same name already exists.");
        } else {
            Group newGroup = new Group();
            newGroup.setName(newGroupName);
            groupService.save(newGroup);
        }
        return "redirect:/group/management";
    }

    @PostMapping("/delete")
    public String deleteGroup(@RequestParam("groupId") long groupId) {
        groupService.deleteGroupById(groupId);
        return "redirect:/group/management";
    }
}
