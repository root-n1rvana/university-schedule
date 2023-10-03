package ua.foxminded.javaspring.kocherga.web_application.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.RedirectAttributesDto;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.GroupMapper;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.RoleMapper;
import ua.foxminded.javaspring.kocherga.web_application.models.mappers.UserMapper;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_LOGIN_EXISTS_ERROR = "Account with this login already exists";
    private static final String ERROR_MSG = "errorMessage";
    private static final String SUCCESS_MSG = "successMessage";


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final GroupServiceImpl groupService;
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;
    private final RoleMapper roleMapper;


    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           GroupServiceImpl groupService,
                           UserMapper userMapper,
                           GroupMapper groupMapper,
                           RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.groupService = groupService;
        this.userMapper = userMapper;
        this.groupMapper = groupMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDto getUserById(long id) {
        return userMapper.userToUserDto(userRepository.getUserById(id));
    }

    @Override
    public User findUserByLoginName(String loginName) {
        return userRepository.findByLogin(loginName);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUsersByGroupId(long groupId) {
        return userRepository.findByOwnerGroupId(groupId);
    }

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
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
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void save(UserDto user) {
        userRepository.save(userMapper.userDtoToUser(user));
    }

    @Transactional
    @Override
    public void saveNewRegisteredUser(UserDto userDto) {
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

    @Override
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

    @Override
    public List<UserDto> getAllTeacherUsers() {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.getRoleByRoleName(RoleName.ROLE_PROFESSOR));
        List<User> students = userRepository.findAllByRolesIn(roles)
                .stream()
                .filter(user -> user.getRoles().stream()
                        .noneMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN)))
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList());
        return userMapper.userListToUserDtoList(students);
    }

    @Transactional
    @Override
    public RedirectAttributesDto saveStudentAndGetRedirAttr(UserDto userDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (userRepository.existsByLogin(userDto.getLogin())) {
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue(USER_LOGIN_EXISTS_ERROR);
        } else {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            Group group = groupService.getGroupById(userDto.getOwnerGroup().getId());
            userDto.setOwnerGroup(groupMapper.groupToGroupDto(group));
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.getRoleByRoleName(RoleName.ROLE_STUDENT));
            userDto.setRoles(roleMapper.roleSetToRoleDtoSet(roles));
            save(userDto);
            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Student added successfully!");
        }
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto saveTeacherAndGetRedirAttr(UserDto userDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (userRepository.existsByLogin(userDto.getLogin())) {
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue(USER_LOGIN_EXISTS_ERROR);
        } else {
//            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//            Group group = groupService.getGroupById(DefaultGroup.PROFESSOR.getId());
//            userDto.setOwnerGroup(groupMapper.groupToGroupDto(group));
//            Set<Role> roles = new HashSet<>();
//            roles.add(roleRepository.getRoleByRoleName(RoleName.ROLE_PROFESSOR));
//            userDto.setRoles(roleMapper.roleSetToRoleDtoSet(roles));

            User newTeacher = new User();
            newTeacher.setFirstname(userDto.getFirstname());
            newTeacher.setLastname(userDto.getLastname());
            newTeacher.setPassword(passwordEncoder.encode(userDto.getPassword()));
            newTeacher.setLogin(userDto.getLogin());
            newTeacher.setOwnerGroup(groupService.getGroupById(DefaultGroup.PROFESSOR.getId()));
            newTeacher.setRoles(Set.of(roleRepository.getRoleByRoleName(RoleName.ROLE_PROFESSOR)));

            save(newTeacher);

            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Teacher added successfully!");
        }
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto updateStudentAndGetRedirAttr(UserDto userDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        User userToEdit = userRepository.getUserById(userDto.getId());
        userToEdit.setFirstname(userDto.getFirstname());
        userToEdit.setLastname(userDto.getLastname());
        Group group = groupService.getGroupById(userDto.getOwnerGroup().getId());
        userToEdit.setOwnerGroup(group);
        save(userToEdit);
        redirectAttributesDto.setName(SUCCESS_MSG);
        redirectAttributesDto.setValue("Student info was modificated successfully!");
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto updateTeacherAndGetRedirAttr(UserDto userDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        User userToEdit = userRepository.getUserById(userDto.getId());
        userToEdit.setFirstname(userDto.getFirstname());
        userToEdit.setLastname(userDto.getLastname());
        save(userToEdit);
        redirectAttributesDto.setName(SUCCESS_MSG);
        redirectAttributesDto.setValue("Teacher info was modificated successfully!");
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto userCredentialsUpdate(UserDto userDto) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        User userToEdit = userRepository.getUserById(userDto.getId());
        if (userRepository.existsByLogin(userDto.getLogin()) && !userToEdit.getLogin().equals(userDto.getLogin())) {
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue(USER_LOGIN_EXISTS_ERROR);
        } else {
            userToEdit.setLogin(userDto.getLogin());
            userToEdit.setPassword(passwordEncoder.encode(userDto.getPassword()));
            save(userToEdit);
            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Credential modification was successful!");
        }
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public RedirectAttributesDto deleteUserAndGetRedirAttr(Long id) {
        RedirectAttributesDto redirectAttributesDto = new RedirectAttributesDto();
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            redirectAttributesDto.setName(SUCCESS_MSG);
            redirectAttributesDto.setValue("Account deleted successfully!");
        } else {
            redirectAttributesDto.setName(ERROR_MSG);
            redirectAttributesDto.setValue("Account not found or could not be deleted");
        }
        return redirectAttributesDto;
    }

    @Transactional
    @Override
    public void deleteUserByLogin(String login) {
        userRepository.deleteByLogin(login);
    }
}
