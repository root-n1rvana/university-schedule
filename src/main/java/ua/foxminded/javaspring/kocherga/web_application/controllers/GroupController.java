package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final static String GROUP_MANAGEMENT_PAGE = "management/group-management";
    private final static String REDIRECT_TO_GROUP_MANAGEMENT_PAGE = "redirect:/group/management";
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/management")
    public String showManagementPage(Model model) {
        List<GroupDto> groups = groupService.getAllGroupsForManagement();
        model.addAttribute("groups", groups);
        return GROUP_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addGroup") //TODO add bad request check from user
    public String addGroup(@RequestParam String newGroupName, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = groupService.saveAndGetRedirAttr(newGroupName);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_GROUP_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update")
    public String updateGroupName(GroupDto groupDto, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = groupService.updateAndGetRedirAttr(groupDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_GROUP_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete")
    public String deleteGroup(@RequestParam long groupId, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = groupService.deleteAndGetRedirAttr(groupId);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_GROUP_MANAGEMENT_PAGE;
    }
}
