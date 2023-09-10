package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ua.foxminded.javaspring.kocherga.web_application.models.Role;
import ua.foxminded.javaspring.kocherga.web_application.models.RoleName;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository,
                       GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
    }

    public List<User> getUsersByGroupId(long groupId) {
        return userRepository.findByOwnerGroupId(groupId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserId(long userId) {
        return userRepository.getUserById(userId);
    }

    @Transactional
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setLogin(userDto.getLoginName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.getRoleById(2L); //id 2L - 'STUDENT' role
        //this code is working now
        Role role2 = roleRepository.getRoleByRoleName(RoleName.ROLE_STUDENT);
        System.out.println("Role: " + role2.toString());
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setOwnerGroup(groupRepository.getGroupById(9L)); //id 9L - 'No Group'
        userRepository.save(user);
    }

    public User findUserByLoginName(String loginName) {
        return userRepository.findByLogin(loginName);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public void checkIfUserExists(UserDto userDto, BindingResult result) {
        User existingUser = this.findUserByLoginName(userDto.getLoginName());

        if (existingUser != null && existingUser.getLogin() != null && !existingUser.getLogin().isEmpty()) {
            result.rejectValue("loginName", "account.exists", "Account with this login already exists");
        }
    }

    public List<User> getUsersByRole(RoleName roleName) {
        return userRepository.findByRoles(roleName);
    }

    public void test () {
        Role role = roleRepository.getRoleByRoleName(RoleName.ROLE_STUDENT);
        try {
            List <User> students = userRepository.getAllByRolesIn(List.of(role));
            students.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
