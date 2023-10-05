package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.*;

@Controller
@RequestMapping("/entity")
public class DatabaseController {

    private final UserServiceImpl userService;
    private final CourseServiceImpl courseService;
    private final RoomServiceImpl roomService;
    private final GroupServiceImpl groupService;
    private final LessonServiceImpl lessonService;
    private final RoleServiceImpl roleService;
    private final LessonTimeServiceImpl lessonTimeService;
    private final ScheduleServiceImpl scheduleService;

    public DatabaseController(UserServiceImpl userService, CourseServiceImpl courseService, RoomServiceImpl roomService,
                              GroupServiceImpl groupService, LessonServiceImpl lessonService, RoleServiceImpl roleService,
                              LessonTimeServiceImpl lessonTimeService, ScheduleServiceImpl scheduleService) {
        this.userService = userService;
        this.courseService = courseService;
        this.roomService = roomService;
        this.groupService = groupService;
        this.lessonService = lessonService;
        this.roleService = roleService;
        this.lessonTimeService = lessonTimeService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public String index() {
        return "db/entity";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "db/users";
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "db/courses";
    }

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "db/rooms";
    }

    @GetMapping("/groups")
    public String getGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "db/groups";
    }

    @GetMapping("/lessons")
    public String getLessons(Model model) {
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "db/lessons";
    }

    @GetMapping("/roles")
    public String getRoles(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "db/roles";
    }

    @GetMapping("/lessonsTime")
    private String getLessonsTime(Model model) {
        model.addAttribute("lessonsTime", lessonTimeService.getAllLessonsTime());
        return "db/lessonsTime";
    }

    @GetMapping("/schedules")
    private String getSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        return "db/schedules";
    }
}
