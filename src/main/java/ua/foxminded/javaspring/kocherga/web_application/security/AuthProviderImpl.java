package ua.foxminded.javaspring.kocherga.web_application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.kocherga.web_application.service.UserDetailsService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthProviderImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(userName);
        String password = authentication.getCredentials().toString();
        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
