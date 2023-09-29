package ua.foxminded.javaspring.kocherga.web_application.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.*;
import ua.foxminded.javaspring.kocherga.web_application.service.CourseService;
import ua.foxminded.javaspring.kocherga.web_application.service.GroupService;
import ua.foxminded.javaspring.kocherga.web_application.service.LessonTimeService;
import ua.foxminded.javaspring.kocherga.web_application.service.RoomService;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.*;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final static String SCHEDULE_MANAGEMENT_PAGE = "management/schedule-management";
    private final static String REDIRECT_TO_SCHEDULE_MANAGEMENT_PAGE = "redirect:/schedule/management";

    private final ScheduleServiceImpl scheduleService;
    private final GroupServiceImpl groupService;
    private final RoomServiceImpl roomService;
    private final LessonTimeServiceImpl lessonTimeService;
    private final CourseServiceImpl courseService;
    private final LessonServiceImpl lessonService;

    public ScheduleController(ScheduleServiceImpl scheduleService, GroupServiceImpl groupService,
                              RoomServiceImpl roomService, LessonTimeServiceImpl lessonTimeService,
                              CourseServiceImpl courseService, LessonServiceImpl lessonService) {
        this.scheduleService = scheduleService;
        this.groupService = groupService;
        this.roomService = roomService;
        this.lessonTimeService = lessonTimeService;
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @GetMapping
    public String index() {
        return "schedule";
    }

    @GetMapping("/management")
    public String showScheduleManagementPage(String yearMonth, Model model) {
        List<GroupDto> groups = groupService.getAllGroupsForStudents();
        List<CourseDto> courses = courseService.getAllCourses();
        List<RoomDto> rooms = roomService.getAllRoomsDto();
        List<LessonTimeDto> lessonsTime = lessonTimeService.getAllLessonsTimeDto();
        model.addAttribute("groups", groups);
        model.addAttribute("courses", courses);
        model.addAttribute("rooms", rooms);
        model.addAttribute("lessonsTime", lessonsTime);
        List<Schedule> scheduleInDateRange = scheduleService.getScheduleInDateRange(yearMonth);
        model.addAttribute("scheduleInDateRange", scheduleInDateRange);
        return SCHEDULE_MANAGEMENT_PAGE;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESSOR')")
    @PostMapping("/addLesson")
    public String addLesson(@ModelAttribute @Valid LessonDto lessonDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        RedirectAttributesDto redirAttrDto = lessonService.saveAndGetRedirAttr(lessonDto, bindingResult);
        redirectAttributes.addFlashAttribute(redirAttrDto.getName(), redirAttrDto.getValue());
        return REDIRECT_TO_SCHEDULE_MANAGEMENT_PAGE;
    }
}
