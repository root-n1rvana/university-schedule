package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    public void testRegistrationSuccess() throws Exception {
        mockMvc.perform(post("/register/save")
                        .param("firstname", "testuser123")
                        .param("lastname", "testuser123")
                        .param("login", "testuser123")
                        .param("password", "testpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
        User user = userRepository.findByLogin("testuser123");
        user.getRoles().clear();
        userRepository.save(user);
        userRepository.delete(user);
    }

    @Test
    public void testRegistrationWithErrors() throws Exception {
        // Set empty values in userDto to trigger errors
        UserDto userDto = new UserDto();

        mockMvc.perform(post("/register/save")
                        .flashAttr("user", userDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/register"));
    }
}
