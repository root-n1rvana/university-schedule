package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static String REDIRECT_TO_STUDENT_MANAGEMENT_PAGE = "redirect:/user/student-management";
    private final static String STUDENT_MANAGEMENT_PAGE = "management/student-management";

    private final UserService userService;
    private final GroupService groupService;

    public UserController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    //Temporal method, will be changed in next tasks
    @GetMapping("/{groupId}")
    public String getUsersByGroupId(@PathVariable int groupId, Model model) {
        List<User> users = userService.getUsersByGroupId(groupId);
        model.addAttribute("users", users);
        return "db/users";
    }

    @GetMapping("/management")
    public String showManagementPage() {
        return "management/user-management";
    }

    @GetMapping("/student-management")
    public String getStudentUsers(Model model) {
        List<UserDto> users = userService.getAllStudentUsers();
        List<GroupDto> groups = groupService.getAllGroupsForStudents();
        model.addAttribute("users", users);
        model.addAttribute("groups", groups);
        return STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) { //
        RedirectAttributesDto redirAttrDto = userService.saveStudentAndgetRedirAttr(userDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.updateStudentAndGetRedirAttr(userDto); //, groupId
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateCredentials")
    public String updateCredentials(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.userCredentialsUpdate(userDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam long userId, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.deleteStudentAndGetRedirAttr(userId);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }
}
