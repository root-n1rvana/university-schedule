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
import ua.foxminded.javaspring.kocherga.web_application.service.impl.GroupServiceImpl;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static String REDIRECT_TO_STUDENT_MANAGEMENT_PAGE = "redirect:/user/student-management";
    private final static String REDIRECT_TO_TEACHER_MANAGEMENT_PAGE = "redirect:/user/teacher-management";
    private final static String STUDENT_MANAGEMENT_PAGE = "management/student-management";
    private final static String TEACHER_MANAGEMENT_PAGE = "management/teacher-management";

    private final UserServiceImpl userService;
    private final GroupServiceImpl groupService;

    public UserController(UserServiceImpl userService, GroupServiceImpl groupService) {
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
    public String showStudentUsersPage(Model model) {
        List<UserDto> users = userService.getAllStudentUsers();
        List<GroupDto> groups = groupService.getAllGroupsForStudents();
        model.addAttribute("users", users);
        model.addAttribute("groups", groups);
        return STUDENT_MANAGEMENT_PAGE;
    }

    @GetMapping("/teacher-management")
    public String showProfManagementPage(Model model) {
        List<UserDto> teachers = userService.getAllTeacherUsers();
        model.addAttribute("teachers", teachers);
        return TEACHER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) { //
        RedirectAttributesDto redirAttrDto = userService.saveStudentAndGetRedirAttr(userDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addTeacher")
    public String addTeacher(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) { //
        RedirectAttributesDto redirAttrDto = userService.saveTeacherAndGetRedirAttr(userDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_TEACHER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.updateStudentAndGetRedirAttr(userDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.updateTeacherAndGetRedirAttr(userDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_TEACHER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateCredentials")
    public String updateCredentials(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.userCredentialsUpdate(userDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateTeacherCredentials")
    public String updateTeacherCredentials(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.userCredentialsUpdate(userDto);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_TEACHER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam long userId, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.deleteUserAndGetRedirAttr(userId);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam long userId, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = userService.deleteUserAndGetRedirAttr(userId);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_TEACHER_MANAGEMENT_PAGE;
    }
}
