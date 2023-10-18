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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

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
        String login = "testuser123";

        mockMvc.perform(post("/register/save")
                        .param("firstname", "testuser123")
                        .param("lastname", "testuser123")
                        .param("login", login)
                        .param("password", "testpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // Verify that Student was added to the database
        assertTrue(userRepository.existsByLogin(login));

        // Cleaning after test
        userRepository.deleteByLogin(login);
        assertFalse(userRepository.existsByLogin(login));
    }

    @Test
    public void testRegistrationError_LoginExist() throws Exception {
        String login = "admin";

        mockMvc.perform(post("/register/save")
                        .param("firstname", "testuser123")
                        .param("lastname", "testuser123")
                        .param("login", login)
                        .param("password", "testpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Account with this login already exists"));
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
