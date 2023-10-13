package ua.foxminded.javaspring.kocherga.web_application.controllers;

import jakarta.validation.Valid;
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

    public ScheduleController(ScheduleService scheduleService,
                              GroupService groupService,
                              RoomService roomService,
                              LessonTimeService lessonTimeService,
                              CourseService courseService,
                              LessonService lessonService) {
        this.scheduleService = scheduleService;
        this.groupService = groupService;
        this.roomService = roomService;
        this.lessonTimeService = lessonTimeService;
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public String showSchedulePage(String yearMonth, Long groupId, Model model) {
        List<ScheduleDto> scheduleInDateRangeForGroup = scheduleService.getScheduleInDateRangeForGroup(groupId, yearMonth);
        model.addAttribute("scheduleInDateRangeForGroup", scheduleInDateRangeForGroup);
        return "schedule";
    }

    //ToDo schedulePage for Teacher

    @GetMapping("/management")
    public String showScheduleManagementPage(String yearMonth, Model model) {
        List<GroupDto> groups = groupService.getAllStudentsGroups();
        List<CourseDto> courses = courseService.getAllCourses();
        List<RoomDto> rooms = roomService.getAllRoomsDto();
        List<LessonTimeDto> lessonsTime = lessonTimeService.getAllLessonsTimeDto();
        model.addAttribute("groups", groups);
        model.addAttribute("courses", courses);
        model.addAttribute("rooms", rooms);
        model.addAttribute("lessonsTime", lessonsTime);
        List<ScheduleDto> scheduleInDateRange = scheduleService.getScheduleInDateRange(yearMonth);
        model.addAttribute("scheduleInDateRange", scheduleInDateRange);
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
