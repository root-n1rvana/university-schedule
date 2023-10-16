package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.UserMapper;
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

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_LOGIN_EXISTS_ERROR = "Account with this login already exists";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final UserMapper userMapper;
    private final RedirectAttributesMessageHandler attrMsgHandler;
    private final BindingResultErrorsHandler bindingResultErrHandler;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           GroupRepository groupRepository,
                           UserMapper userMapper,
                           RedirectAttributesMessageHandler attrMsgHandler,
                           BindingResultErrorsHandler bindingResultErrHandler) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
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
        Page<User> userPage = userRepository.findByRoleName(RoleName.ROLE_PROFESSOR, pageable);
//        List<User> filteredUsers = userPage
//                .getContent()
//                .stream()
//                .filter(user -> user.getRoles().stream()
//                        .noneMatch(role -> role.getRoleName() == RoleName.ROLE_ADMIN))
//                .collect(Collectors.toList());
//        Page<User> filteredUserPage = new PageImpl<>(filteredUsers, pageable, filteredUsers.size());
        return userMapper.pageUserToPageUserDto(userPage);
    }

    @Override
    public Page<UserDto> getAllStudents(Pageable pageable) { //todo
        Page<User> userPage = userRepository.findByRoleName(RoleName.ROLE_STUDENT, pageable);
        Stream<User> userStream = userPage.get()
                .filter(user -> user.getRoles().stream()
                        .noneMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN)))
                .sorted(Comparator.comparing(User::getId));
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
    public void userCredentialsUpdate(UserDto userDto, BindingResult bindingResult, RedirectAttributes
            redirectAttributes) {
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
//        if (userDto.getOwnerGroup() != null) {
//            user.setOwnerGroup(groupRepository.getGroupById(userDto.getOwnerGroup().getId()));
//        }
        user.setOwnerGroup(null);
        if (userDto.getRoles2() == null) {
            user.setRoles(Set.of(roleRepository.getRoleByRoleName(RoleName.ROLE_STUDENT)));
        } else {
            user.setRoles(roleRepository.findAllByRoleNameIn(userDto.getRoles2()));
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
