package ua.foxminded.javaspring.kocherga.web_application.service;

import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(long id);

    User findUserByLoginName(String loginName);

    List<User> getAllUsers();

    List<UserDto> getAllStudentUsers();

    List<User> getUsersByGroupId(long groupId);

    boolean existsByLogin(String login);

    boolean existsById(Long id);

    void save(User user);

    void save(UserDto user);

    void saveNewRegisteredUser(UserDto userDto);

    RedirectAttributesDto saveStudentAndgetRedirAttr(UserDto userDto);

    RedirectAttributesDto updateStudentAndGetRedirAttr(UserDto userDto);

    RedirectAttributesDto userCredentialsUpdate(UserDto userDto);

    RedirectAttributesDto deleteStudentAndGetRedirAttr(Long id);

    void deleteUserByLogin(String login);





}
