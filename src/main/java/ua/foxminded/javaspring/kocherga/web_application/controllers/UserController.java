package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;
import ua.foxminded.javaspring.kocherga.web_application.service.RoleService;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    private final static String REDIRECT_TO_STUDENT_MANAGEMENT_PAGE = "redirect:/user/student-management";
    private final static String REDIRECT_TO_TEACHER_MANAGEMENT_PAGE = "redirect:/user/teacher-management";
    private final static String REDIRECT_TO_USER_MANAGEMENT_PAGE = "redirect:/user/user-management";
    private final static String STUDENT_MANAGEMENT_PAGE = "management/student-management";
    private final static String TEACHER_MANAGEMENT_PAGE = "management/teacher-management";

    private final UserService userService;
    private final GroupService groupService;
    private final RoleService roleService;

    public UserController(UserService userService, GroupService groupService, RoleService roleService) {
        this.userService = userService;
        this.groupService = groupService;
        this.roleService = roleService;
    }

    @GetMapping("/user-management")
    public String showUserManagementPage(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("page", userService.getUsersPage(pageable));
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("roles", roleService.getAllRoles());
        return "management/user-management";
    }

    @GetMapping("/student-management")
    public String showStudentManagementPage(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("page", userService.getAllStudents(pageable));
        model.addAttribute("groups", groupService.getAllStudentsGroups());
        return "management/student-management";
    }

    @GetMapping("/teacher-management")
    public String showTeacherManagementPage(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("page", userService.getAllTeacherUsers(pageable));
        return TEACHER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.saveNewUser(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_USER_MANAGEMENT_PAGE;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) { //
        userService.saveNewUser(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addTeacher")
    public String addTeacher(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) { //
        userService.saveNewUser(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_TEACHER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.updateUser(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_USER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.updateUser(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.updateUser(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_TEACHER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateUserCredentials")
    public String updateUserCredentials(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.userCredentialsUpdate(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_USER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateStudentCredentials")
    public String updateStudentCredentials(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.userCredentialsUpdate(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateTeacherCredentials")
    public String updateTeacherCredentials(@ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.userCredentialsUpdate(userDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_TEACHER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam long userId, RedirectAttributes redirectAttributes) {
        userService.deleteUser(userId, redirectAttributes);
        return REDIRECT_TO_USER_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam long userId, RedirectAttributes redirectAttributes) {
        userService.deleteUser(userId, redirectAttributes);
        return REDIRECT_TO_STUDENT_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam long userId, RedirectAttributes redirectAttributes) {
        userService.deleteUser(userId, redirectAttributes);
        return REDIRECT_TO_TEACHER_MANAGEMENT_PAGE;
    }
}
