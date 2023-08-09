package ua.foxminded.javaspring.kocherga.web_application.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.*;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.RoleRepository;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;

    @Autowired
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

    public void saveUser(UserDto userDto) { //TODO - check this method. Imo it can be much better
        User user = new User();
        user.setUserName(userDto.getFirstName());
        user.setUserLastname(userDto.getLastName());
        user.setLoginName(userDto.getLoginName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.getRoleById(2L); //id 2L - 'STUDENT' role
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setOwnerGroup(groupRepository.getGroupById(9L)); //id 9L - 'No Group'
        userRepository.save(user);
    }

    public void saveUser(User userDto) { //TODO - check this method. Imo it can be much better
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setUserLastname(userDto.getUserLastname());
        user.setLoginName(userDto.getLoginName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.getRoleById(2L); //id 2L - 'STUDENT' role
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setOwnerGroup(groupRepository.getGroupById(9L)); //id 9L - 'No Group'
        userRepository.save(user);
    }

    public User findUserByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

    @Transactional
    public void saveAll(List<User> users) {
        for (User user : users) {
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                existingUser.setOwnerGroup(user.getOwnerGroup());
                existingUser.setRoles(user.getRoles());
                userRepository.save(existingUser);
            }
        }
    }
}
