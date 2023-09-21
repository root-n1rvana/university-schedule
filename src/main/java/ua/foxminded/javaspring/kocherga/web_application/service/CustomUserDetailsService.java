package ua.foxminded.javaspring.kocherga.web_application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.UserDetailsImpl;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(loginName);
        Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException(String.format("user by login %s not found", loginName)));
        return new UserDetailsImpl(user);
    }
}
