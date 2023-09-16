package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.UserMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String USER_LOGIN_EXISTS_ERROR = "Account with this login already exists";
    private static final String ERROR_MSG = "errorMessage";
    private static final String SUCCESS_MSG = "successMessage";


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final GroupService groupService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository,
                       GroupService groupService,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.groupService = groupService;
        this.userMapper = userMapper;
    }

    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }

    public User findUserByLoginName(String loginName) {
        return userRepository.findByLogin(loginName);
    }

    public List<User> getUsersByGroupId(long groupId) {
        return userRepository.findByOwnerGroupId(groupId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public void checkIfUserExists(UserDto userDto, BindingResult result) {
        User existingUser = this.findUserByLoginName(userDto.getLogin());

        if (existingUser != null && existingUser.getLogin() != null && !existingUser.getLogin().isEmpty()) {
            result.rejectValue("loginName", "account.exists", USER_LOGIN_EXISTS_ERROR);
        }
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.getRoleByRoleName(RoleName.ROLE_STUDENT);
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setOwnerGroup(groupService.getGroupById(9L)); //id 9L - default 'No Group'
        userRepository.save(user);
    }

    public List<UserDto> getAllStudentUsers() {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.getRoleByRoleName(RoleName.ROLE_STUDENT));
        List<User> students = userRepository.findAllByRolesIn(roles)
                .stream()
                .filter(user -> user.getRoles().stream()
                        .noneMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN)))
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList());
        return userMapper.userListToUserDtoList(students);
    }

    @Transactional
    public RedirectAttributesDto saveStudentWithRedirAttr(String firstname, String lastname, String login, String password, String groupName) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto(login);
        if (userRepository.existsByLogin(login)) {
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue(USER_LOGIN_EXISTS_ERROR);
        } else {
            User user = new User();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password));
            Group group = groupService.saveGroupOrIgnoreIfExists(groupName);
            user.setOwnerGroup(group);
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.getRoleByRoleName(RoleName.ROLE_STUDENT));
            user.setRoles(roles);
            save(user);
            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Student added successfully!");
        }
        return redirectAttributesDto;
    }

    @Transactional
    public RedirectAttributesDto updateStudentWithRedirAttr(Long userId, String firstname, String lastname, String groupName) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        User userToEdit = userRepository.getUserById(userId);
        userToEdit.setFirstname(firstname);
        userToEdit.setLastname(lastname);
        Group group = groupService.saveGroupOrIgnoreIfExists(groupName);
        userToEdit.setOwnerGroup(group);
        save(userToEdit);
        redirectAttributesDto.setName(SUCCESS_MSG);
        redirectAttributesDto.setValue("Student info was modificated successfully!");
        return redirectAttributesDto;
    }

    @Transactional
    public RedirectAttributesDto deleteStudentWithRedirAttr(Long id) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Student deleted successfully!");
        } else {
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue("Student not found or could not be deleted");
        }
        return redirectAttributesDto;
    }

    @Transactional
    public void deleteUserByLogin(String login) {
        userRepository.deleteByLogin(login);
    }

    @Transactional
    public RedirectAttributesDto userCredentialsUpdate(Long userId, String login, String password) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        User userToEdit = userRepository.getUserById(userId);
        if (userRepository.existsByLogin(login) && !userToEdit.getLogin().equals(login)) {
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue(USER_LOGIN_EXISTS_ERROR);
        } else {
            userToEdit.setLogin(login);
            userToEdit.setPassword(passwordEncoder.encode(password));
            save(userToEdit);
            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Credential modification was successful!");
        }
        return redirectAttributesDto;
    }
}
