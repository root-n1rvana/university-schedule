package ua.foxminded.javaspring.kocherga.web_application.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.*;
import ua.foxminded.javaspring.kocherga.web_application.service.*;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final static String SCHEDULE_MANAGEMENT_PAGE = "management/schedule-management";
    private final static String REDIRECT_TO_SCHEDULE_MANAGEMENT_PAGE = "redirect:/schedule/management";

    private final ScheduleService scheduleService;
    private final GroupService groupService;
    private final RoomService roomService;
    private final LessonTimeService lessonTimeService;
    private final CourseService courseService;
    private final LessonService lessonService;

    private final UserService userService;

    public ScheduleController(ScheduleService scheduleService,
                              GroupService groupService,
                              RoomService roomService,
                              LessonTimeService lessonTimeService,
                              CourseService courseService,
                              LessonService lessonService,
                              UserService userService) {
        this.scheduleService = scheduleService;
        this.groupService = groupService;
        this.roomService = roomService;
        this.lessonTimeService = lessonTimeService;
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @GetMapping
    public String showSchedulePage() {
        return "schedule";
    }

    @GetMapping("/teacher/month")
    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    public String getTeacherScheduleByMonth(@ModelAttribute ScheduleDto scheduleDto, Model model) {
        model.addAttribute("teacherScheduleByMonth", scheduleService.getTeacherScheduleByMonth(scheduleDto));
        return "schedule";
    }

    @GetMapping("/teacher/day")
    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    public String getTeacherScheduleByDay(@ModelAttribute ScheduleDto scheduleDto, Model model) {
        List<ScheduleDto> scheduleForTeacherDay = scheduleService.getTeacherScheduleByDay(scheduleDto);
        model.addAttribute("teacherScheduleByDay", scheduleForTeacherDay);
        return "schedule";
    }

    @GetMapping("/group/month")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String getGroupScheduleByMonth(@ModelAttribute ScheduleDto scheduleDto, Model model, BindingResult bindingResult) {
        List<ScheduleDto> scheduleInDateRangeForGroup = scheduleService.getGroupScheduleByMonth(scheduleDto, bindingResult);
        model.addAttribute("groupScheduleByMonth", scheduleInDateRangeForGroup);
        return "schedule";
    }

    @GetMapping("/group/day")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String getGroupScheduleByDay(@ModelAttribute ScheduleDto scheduleDto, Model model) {
        List<ScheduleDto> scheduleForStudentDay = scheduleService.getGroupScheduleByDay(scheduleDto);
        model.addAttribute("groupScheduleByDay", scheduleForStudentDay);
        return "schedule";
    }

    @GetMapping("/management")
    public String showScheduleManagementPage(String yearMonth, Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("groups", groupService.getAllStudentsGroups());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("rooms", roomService.getAllRoomsDto());
        model.addAttribute("lessonsTime", lessonTimeService.getAllLessonsTimeDto());
        model.addAttribute("scheduleInDateRange", scheduleService.getScheduleInDateRange(yearMonth));
        model.addAttribute("professorCourses", userService.getAllTeacherUsers(pageable));
        return SCHEDULE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addLesson")
    public String addLesson(@ModelAttribute @Valid LessonDto lessonDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        lessonService.saveNewLesson(lessonDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_SCHEDULE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/update")
    public String updateLesson(LessonDto lessonDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        lessonService.updateLesson(lessonDto, bindingResult, redirectAttributes);
        return REDIRECT_TO_SCHEDULE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/delete")
    public String deleteLesson(@RequestParam long lessonId, RedirectAttributes redirectAttributes) {
        lessonService.deleteLesson(lessonId, redirectAttributes);
        return REDIRECT_TO_SCHEDULE_MANAGEMENT_PAGE;
    }
}
