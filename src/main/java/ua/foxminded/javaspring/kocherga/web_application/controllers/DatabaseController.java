package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.CourseDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.*;

import java.util.List;

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
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "db/users";
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        List<CourseDto> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "db/courses";
    }

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "db/rooms";
    }

    @GetMapping("/groups")
    public String getGroups(Model model) {
        List<GroupDto> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "db/groups";
    }

    @GetMapping("/lessons")
    public String getLessons(Model model) {
        List<Lesson> lessons = lessonService.getAllLessons();
        model.addAttribute("lessons", lessons);
        return "db/lessons";
    }

    @GetMapping("/roles")
    public String getRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "db/roles";
    }

    @GetMapping("/lessonsTime")
    private String getLessonsTime(Model model) {
        List<LessonTime> lessonsTime = lessonTimeService.getAllLessonsTime();
        model.addAttribute("lessonsTime", lessonsTime);
        return "db/lessonsTime";
    }

    @GetMapping("/schedules")
    private String getSchedules(Model model) {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        model.addAttribute("schedules", schedules);
        return "db/schedules";
    }
}
