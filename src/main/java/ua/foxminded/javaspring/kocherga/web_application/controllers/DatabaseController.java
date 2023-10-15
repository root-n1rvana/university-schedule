package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.foxminded.javaspring.kocherga.web_application.service.*;

@Controller
@RequestMapping("/entity")
public class DatabaseController {

    private final UserService userService;
    private final CourseService courseService;
    private final RoomService roomService;
    private final GroupService groupService;
    private final LessonService lessonService;
    private final RoleService roleService;
    private final LessonTimeService lessonTimeService;
    private final ScheduleService scheduleService;

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
        return "db/entity";
    }

    @GetMapping("/users")
    public String getUsers(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("page", userService.getUsersPage(pageable));
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
    public String getLessons(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("page", lessonService.getAllLessons(pageable));
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
