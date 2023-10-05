package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import ua.foxminded.javaspring.kocherga.web_application.models.DefaultGroup;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(long id);

    User findUserByLoginName(String loginName);

    List<UserDto> getAllUsers();

    List<UserDto> getAllStudentUsers();

    List<UserDto> getAllTeacherUsers();

    List<User> getUsersByGroupId(long groupId);

    boolean existsByLogin(String login);

    boolean existsById(Long id);

    void save(User user);

    void save(UserDto user);

    void saveNewRegisteredUser(UserDto userDto);


    RedirectAttributesDto saveStudentAndGetRedirAttr(UserDto userDto);

    RedirectAttributesDto saveTeacherAndGetRedirAttr(UserDto userDto);

    @Transactional
    RedirectAttributesDto updateUserAndGetRedirAttr(UserDto userDto);

    RedirectAttributesDto updateStudentAndGetRedirAttr(UserDto userDto);

    RedirectAttributesDto userCredentialsUpdate(UserDto userDto);

    RedirectAttributesDto deleteUserAndGetRedirAttr(Long id);
}
