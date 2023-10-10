package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    List<UserDto> getAllStudentUsers();

    List<UserDto> getAllTeacherUsers();

    @Transactional
    void saveNewRegisteredUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void saveNewUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void updateUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void userCredentialsUpdate(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @Transactional
    void deleteUser(Long id, RedirectAttributes redirectAttributes);
}
