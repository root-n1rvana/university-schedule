package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;

public interface UserService {

    Page<UserDto> getAllStudents(Pageable pageable);

    Page<UserDto> getAllTeacherUsers(Pageable pageable);

    Page<UserDto> getUsersPage(Pageable pageable);

    void saveNewRegisteredUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    void saveNewUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    void updateUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    void userCredentialsUpdate(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    void deleteUser(Long id, RedirectAttributes redirectAttributes);
}
