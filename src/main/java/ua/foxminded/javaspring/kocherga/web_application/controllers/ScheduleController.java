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
    public String showSchedulePage(@ModelAttribute ScheduleDto scheduleDto, Model model) {
        List<ScheduleDto> scheduleInDateRangeForGroup = scheduleService.getScheduleInDateRangeForGroup(scheduleDto);
        List<ScheduleDto> scheduleForStudentDay = scheduleService.getScheduleForDayForGroup(scheduleDto);
        model.addAttribute("scheduleInDateRangeForGroup", scheduleInDateRangeForGroup);
        model.addAttribute("scheduleForStudentDay", scheduleForStudentDay);

        List<ScheduleDto> scheduleInDateRangeForTeacher = scheduleService.getScheduleInDateRangeForTeacher(scheduleDto);
        List<ScheduleDto> scheduleForTeacherDay = scheduleService.getScheduleForDayForTeacher(scheduleDto);
        model.addAttribute("scheduleInDateRangeForTeacher", scheduleInDateRangeForTeacher);
        model.addAttribute("scheduleForTeacherDay", scheduleForTeacherDay);
        return "schedule";
    }

    @GetMapping("/management")
    public String showScheduleManagementPage(String yearMonth, Model model) {
        model.addAttribute("groups", groupService.getAllStudentsGroups());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("rooms", roomService.getAllRoomsDto());
        model.addAttribute("lessonsTime", lessonTimeService.getAllLessonsTimeDto());
        model.addAttribute("scheduleInDateRange", scheduleService.getScheduleInDateRange(yearMonth));
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
