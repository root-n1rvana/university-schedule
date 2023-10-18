package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Course;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.UserMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.CourseRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.BindingResultErrorsHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.RedirectAttributesMessageHandler;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.RegistrationValidationException;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.StudentValidationException;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.TeacherValidationException;
import ua.foxminded.javaspring.kocherga.web_application.service.exceptions.UserValidationException;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_LOGIN_EXISTS_ERROR = "Account with this login already exists";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final UserMapper userMapper;
    private final RedirectAttributesMessageHandler attrMsgHandler;
    private final BindingResultErrorsHandler bindingResultErrHandler;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           GroupRepository groupRepository,
                           CourseRepository courseRepository,
                           UserMapper userMapper,
                           RedirectAttributesMessageHandler attrMsgHandler,
                           BindingResultErrorsHandler bindingResultErrHandler) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.userMapper = userMapper;
        this.attrMsgHandler = attrMsgHandler;
        this.bindingResultErrHandler = bindingResultErrHandler;
    }

    @Transactional
    @Override
    public void saveNewRegisteredUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.RegistrationBindingResultErrors(bindingResult);
        checkIfUserExists(userDto);
        User user = new User();
        fillUserByUserDto(userDto, user);
        user.setOwnerGroup(null);
        userRepository.save(user);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "You have successfully registered!");
    }

    public void checkIfUserExists(UserDto userDto) {
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new RegistrationValidationException(USER_LOGIN_EXISTS_ERROR);
        }
    }

    @Override
    public Page<UserDto> getUsersPage(Pageable pageable) {
        Page<User> usersPage = userRepository.findAllByOrderByIdAsc(pageable);
        return userMapper.pageUserToPageUserDto(usersPage);
    }

    @Override
    public Page<UserDto> getAllTeacherUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findByRoleNameExceptAdminGroup(RoleName.ROLE_PROFESSOR, pageable);
        return userMapper.pageUserToPageUserDto(userPage);
    }

    @Override
    public Page<UserDto> getAllStudents(Pageable pageable) {
        Page<User> userPage = userRepository.findByRoleNameExceptAdminGroup(RoleName.ROLE_STUDENT, pageable);
        return userMapper.pageUserToPageUserDto(userPage);
    }

    @Transactional
    @Override
    public void saveNewUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateUserBindingResultErrors(bindingResult);
        checkLoginDuplication(userDto);
        User user = new User();
        fillUserByUserDto(userDto, user);
        user = userRepository.saveAndFlush(user);
        user.setOwnerGroup(groupRepository.getGroupById(userDto.getOwnerGroup().getId()));
        userRepository.saveAndFlush(user);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "User added successfully!");
    }

    private void checkLoginDuplication(UserDto userDto) {
        boolean notSameLoginName = true;
        if (!(userDto.getId() == null)) {
            notSameLoginName = !userRepository.getUserById(userDto.getId()).getLogin().equals(userDto.getLogin());
        }
        switch (userDto.getUiPage()) {
            case "teacher":
                if (userRepository.existsByLogin(userDto.getLogin()) && notSameLoginName) {
                    throw new TeacherValidationException(USER_LOGIN_EXISTS_ERROR);
                }
                break;
            case "student":
                if (userRepository.existsByLogin(userDto.getLogin()) && notSameLoginName) {
                    throw new StudentValidationException(USER_LOGIN_EXISTS_ERROR);
                }
                break;
            case "user":
                if (userRepository.existsByLogin(userDto.getLogin()) && notSameLoginName) {
                    throw new UserValidationException(USER_LOGIN_EXISTS_ERROR);
                }
                break;
        }
    }

    @Transactional
    @Override
    public void updateUser(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateUserBindingResultErrors(bindingResult);
        checkLoginDuplication(userDto);
        User userToEdit = userRepository.getUserById(userDto.getId());
        fillUserByUserDto(userDto, userToEdit);
        userRepository.save(userToEdit);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "User updated successfully!");
    }

    @Transactional
    @Override
    public void userCredentialsUpdate(UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        bindingResultErrHandler.validateUserBindingResultErrors(bindingResult);
        checkLoginDuplication(userDto);
        User userToEdit = userRepository.getUserById(userDto.getId());
        fillUserByUserDto(userDto, userToEdit);
        userRepository.save(userToEdit);
        attrMsgHandler.setSuccessMessage(redirectAttributes, "Credential modification was successful!");
    }

    private void fillUserByUserDto(UserDto userDto, User user) {
        if (userDto.getFirstname() != null) {
            user.setFirstname(userDto.getFirstname());
        }
        if (userDto.getLastname() != null) {
            user.setLastname(userDto.getLastname());
        }
        if (userDto.getLogin() != null) {
            user.setLogin(userDto.getLogin());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (userDto.getProfessorCourses() != null) {
            Set<Course> newProfessorCourse = new HashSet<>();
            String courseName = userDto.getProfessorCourses().iterator().next().getCourseName();
            newProfessorCourse.add(courseRepository.getCourseByCourseName(courseName));
            user.setProfessorCourses(newProfessorCourse);
        }
//        if (userDto.getOwnerGroup() != null) {
//            user.setOwnerGroup(groupRepository.getGroupById(userDto.getOwnerGroup().getId()));
//        }
        if (userDto.getRoles() == null) {
            Set<Role> studentRole = new HashSet<>();
            studentRole.add(roleRepository.getRoleByRoleName(RoleName.ROLE_STUDENT));
            user.setRoles(studentRole);
        } else {
            user.setRoles(roleRepository.findAllByRoleNameIn(userDto.getRoles()));
        }
    }

    @Transactional
    @Override
    public void deleteUser(Long id, RedirectAttributes redirectAttributes) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            attrMsgHandler.setSuccessMessage(redirectAttributes, "Account deleted successfully!");
        } else {
            attrMsgHandler.setErrorMessage(redirectAttributes, "Account not found or could not be deleted");
        }
    }
}
