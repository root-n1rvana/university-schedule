package ua.foxminded.javaspring.kocherga.web_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/register/**")
                        .permitAll()
                        .requestMatchers("/user/management/**").hasRole("ADMIN")
                        .requestMatchers("/course/addCourse").hasAnyRole("ADMIN", "PROFESSOR")
                        .requestMatchers("/group/addGroup").hasAnyRole("ADMIN", "PROFESSOR")
                        .requestMatchers("/schedule/addLesson").hasAnyRole("ADMIN", "PROFESSOR")

                        .requestMatchers("/user/addUser").hasRole("ADMIN")
                        .requestMatchers("/user/addStudent").hasAnyRole("ADMIN", "PROFESSOR")
                        .requestMatchers("/user/addTeacher").hasRole("ADMIN")

                        .requestMatchers("/user/updateUser").hasRole("ADMIN")
                        .requestMatchers("/user/updateStudent").hasAnyRole("ADMIN", "PROFESSOR")
                        .requestMatchers("/user/updateTeacher").hasRole("ADMIN")
                        .requestMatchers("/schedule/update").hasAnyRole("ADMIN", "PROFESSOR")

                        .requestMatchers("/course/delete").hasRole("ADMIN")
                        .requestMatchers("/group/delete").hasRole("ADMIN")
                        .requestMatchers("/schedule/delete").hasAnyRole("ADMIN", "PROFESSOR")

                        .requestMatchers("/user/deleteUser").hasRole("ADMIN")
                        .requestMatchers("/user/deleteStudent").hasRole("ADMIN")
                        .requestMatchers("/user/deleteTeacher").hasRole("ADMIN")

                        .requestMatchers("/user/updateUserCredentials").hasRole("ADMIN")
                        .requestMatchers("/user/updateStudentCredentials").hasRole("ADMIN")
                        .requestMatchers("/user/updateTeacherCredentials").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
