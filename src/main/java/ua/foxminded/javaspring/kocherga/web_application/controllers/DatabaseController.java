package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.service.*;

import java.util.List;

@Controller
@RequestMapping("/database")
public class DatabaseController {

    private final UserService userService;
    private final CourseService courseService;
    private final RoomService roomService;
    private final GroupService groupService;
    private final LessonService lessonService;
    private final RoleService roleService;
    private final LessonTimeService lessonTimeService;
    private final ScheduleService scheduleService;

    @Autowired
    public DatabaseController(UserService userService, CourseService courseService, RoomService roomService,
                              GroupService groupService, LessonService lessonService, RoleService roleService,
                              LessonTimeService lessonTimeService, ScheduleService scheduleService) {
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
        return "database";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

    @GetMapping("/groups")
    public String getGroups(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("/lessons")
    public String getLessons(Model model) {
        List<Lesson> lessons = lessonService.getAllLessons();
        model.addAttribute("lessons", lessons);
        return "lessons";
    }

    @GetMapping("/roles")
    public String getRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @GetMapping("/lessonsTime")
    private String getLessonsTime(Model model) {
        List<LessonTime> lessonsTime = lessonTimeService.getAllLessonsTime();
        model.addAttribute("lessonsTime", lessonsTime);
        return "lessonsTime";
    }

    @GetMapping("/schedules")
    private String getSchedules(Model model) {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        model.addAttribute("schedules", schedules);
        return "schedules";
    }
}