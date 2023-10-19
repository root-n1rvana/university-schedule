package ua.foxminded.javaspring.kocherga.web_application.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.service.CourseService;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final static String GROUP_MANAGEMENT_PAGE = "management/group-management";
    private final static String REDIRECT_TO_GROUP_MANAGEMENT_PAGE = "redirect:/group/management";

    private final GroupService groupService;
    private final CourseService courseService;

    public GroupController(GroupService groupService, CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    @GetMapping("/management")
    public String showManagementPage(Model model) {
        List<GroupDto> groups = groupService.getAllGroupsForManagement();
        List<CourseDto> courses = courseService.getAllCourses();
        model.addAttribute("groups", groups);
        model.addAttribute("courses", courses);
        return GROUP_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addGroup")
    public String addGroup(@ModelAttribute @Valid GroupDto groupDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        groupService.saveNewGroup(groupDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_GROUP_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update")
    public String updateGroup(GroupDto groupDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        groupService.updateGroup(groupDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_GROUP_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete")
    public String deleteGroup(@RequestParam long groupId, RedirectAttributes redirectAttributes) {
        groupService.deleteGroup(groupId, redirectAttributes);
        return REDIRECT_TO_GROUP_MANAGEMENT_PAGE;
    }
}
