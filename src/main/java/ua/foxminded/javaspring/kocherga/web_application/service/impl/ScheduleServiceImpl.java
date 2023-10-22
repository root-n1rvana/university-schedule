package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.ScheduleDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.ScheduleMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.ScheduleRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.BindingResultErrorsHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.RedirectAttributesMessageHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.ScheduleService;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.ScheduleValidationException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final ScheduleMapper scheduleMapper;
    private final RedirectAttributesMessageHandler attrMsgHandler;
    private final BindingResultErrorsHandler bindingResultErrHandler;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               GroupRepository groupRepository,
                               ScheduleMapper scheduleMapper,
                               RedirectAttributesMessageHandler attrMsgHandler,
                               BindingResultErrorsHandler bindingResultErrHandler) {
        this.scheduleRepository = scheduleRepository;
        this.groupRepository = groupRepository;
        this.scheduleMapper = scheduleMapper;
        this.attrMsgHandler = attrMsgHandler;
        this.bindingResultErrHandler = bindingResultErrHandler;
    }

    @Override
    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(scheduleMapper::scheduleToScheduleDto)
                .toList();
    }

    @Override //todo refactor
    public List<ScheduleDto> getScheduleInDateRange(String yearMonth) {
        if (yearMonth == null || yearMonth.isEmpty()) {
            return Collections.emptyList();
        }
        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();
        List<Schedule> schedules = scheduleRepository.findScheduleInDateRange(startDate, endDate);
        return scheduleMapper.scheduleListToScheduleDtoList(schedules);
    }

    @Override
    @Transactional
    public List<ScheduleDto> getGroupScheduleByMonth(ScheduleDto scheduleDto, BindingResult bindingResult) {
        bindingResultErrHandler.validateScheduleBindingResultErrors(bindingResult);
        if (isInvalidInput(scheduleDto.getYearMonth(), String.valueOf(scheduleDto.getGroupId()))) {
            throw new ScheduleValidationException("not enough data for get schedule");
        }
        String yearMonth = scheduleDto.getYearMonth();
        Long groupId = scheduleDto.getGroupId();

        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        String groupName = groupRepository.findById(groupId)
                .orElseThrow( () -> new ValidationException("no group found"))
                .getName();

        List<Schedule> scheduleList = scheduleRepository.findScheduleByGroupAndDateBetween(groupId, startDate, endDate);
        if (scheduleList.isEmpty()) {
            throw new ScheduleValidationException(String.format("group %s has not schedule for month %s", groupName, yearMonth));
        }
        return scheduleMapper.scheduleListToScheduleDtoList(scheduleList);
    }

    @Override
    public List<ScheduleDto> getGroupScheduleByDay(ScheduleDto scheduleDto) {
        if (isInvalidInput(scheduleDto.getYearMonthDay(), String.valueOf(scheduleDto.getGroupId()))) {
            throw new ScheduleValidationException("not enough data for get schedule");
        }
        String yearMonthDay = scheduleDto.getYearMonthDay();
        Long groupId = scheduleDto.getGroupId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(yearMonthDay, formatter);

        // this can be removed by adding in html form hidden field groupName and put it into ScheduleDto
        String groupName = groupRepository.findById(groupId)
                .orElseThrow( () -> new ValidationException("no group found"))
                .getName();

        List<Schedule> scheduleList = scheduleRepository.findScheduleByGroupAndDateBetween(groupId, localDate, localDate);
        if (scheduleList.isEmpty()) {
            throw new ScheduleValidationException(String.format("group %s has not schedule for day %s", groupName, yearMonthDay));
        }
        return scheduleMapper.scheduleListToScheduleDtoList(scheduleList);
    }

    @Override
    @Transactional
    public List<ScheduleDto> getTeacherScheduleByMonth(ScheduleDto scheduleDto) {
        if (isInvalidInput(scheduleDto.getYearMonth(), scheduleDto.getProfessorLogin())) {
            throw new ScheduleValidationException("not enough data for get schedule");
        }
        String yearMonth = scheduleDto.getYearMonth();
        String professorLogin = scheduleDto.getProfessorLogin();

        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        List<Schedule> scheduleList = scheduleRepository.findScheduleByProfessorLoginAndDateBetween(professorLogin, startDate, endDate);
        List<ScheduleDto> scheduleDtoList = scheduleMapper.scheduleListToScheduleDtoList(scheduleList);
        if (scheduleDtoList.isEmpty()) {
            throw new ScheduleValidationException(String.format("professor %s has not schedule for month %s", professorLogin, yearMonth));
        }
        return scheduleDtoList;
    }

    @Override
    @Transactional
    public List<ScheduleDto> getTeacherScheduleByDay(ScheduleDto scheduleDto) {
        if (isInvalidInput(scheduleDto.getYearMonthDay(), scheduleDto.getProfessorLogin())) {
            throw new ScheduleValidationException("not enough data for get schedule");
        }
        String yearMonthDay = scheduleDto.getYearMonthDay();
        String professorLogin = scheduleDto.getProfessorLogin();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(yearMonthDay, formatter);

        List<Schedule> scheduleList = scheduleRepository.findScheduleByProfessorLoginAndDateBetween(professorLogin, localDate, localDate);
        List<ScheduleDto> scheduleDtoList = scheduleMapper.scheduleListToScheduleDtoList(scheduleList);
        if (scheduleDtoList.isEmpty()) {
            throw new ScheduleValidationException(String.format("professor %s has not schedule for day %s", professorLogin, yearMonthDay));
        }
        return scheduleDtoList;
    }

    private boolean isInvalidInput(String ... values) {
        return Arrays.stream(values)
                .anyMatch(value -> value == null || value.isEmpty());
    }
}
