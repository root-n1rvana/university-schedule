package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.UserDto;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.GroupServiceImpl;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.UserServiceImpl;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GroupServiceImpl groupService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Id's for test purpose
    long groupId = 1L;
    long userId = 43L;

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testManagementPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/management"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("management/user-management"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testStudentManagementPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/student-management"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("management/student-management"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(37)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(43L)),
                                hasProperty("login", is("test1"))
                        )
                )));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testAddStudent_ProfessorAccess() throws Exception {
        String firstname = "TestStd";
        String lastname = "TestStd";
        String login = "teststd1";
        String password = "pass";
        Long groupId = 3L;

        mockMvc.perform(post("/user/addStudent")
                        .param("firstname", firstname)
                        .param("lastname", lastname)
                        .param("login", login)
                        .param("password", password)
                        .param("ownerGroup.id", String.valueOf(groupId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/student-management"))
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(flash().attribute("successMessage", "Student added successfully!"));

        // Verify that Student was added to the database
        assertTrue(userService.existsByLogin(login));

        // Cleaning after test
        userService.deleteUserByLogin(login);
        assertFalse(userService.existsByLogin(login));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddExistingStudent() throws Exception {
        String firstname = "TestStd";
        String lastname = "TestStd";
        String login = "test1";
        String password = "pass";
        Long groupId = 3L;

        mockMvc.perform(post("/user/addStudent")
                        .param("firstname", firstname)
                        .param("lastname", lastname)
                        .param("login", login)
                        .param("password", password)
                        .param("groupId", String.valueOf(groupId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/student-management"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Account with this login already exists"));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testAddStudent_StudentAccess() throws Exception {
        String firstname = "TestStd";
        String lastname = "TestStd";
        String login = "test1";
        String password = "pass";
        String groupName = "GR-99";

        mockMvc.perform(post("/user/addStudent")
                        .param("firstname", firstname)
                        .param("lastname", lastname)
                        .param("login", login)
                        .param("password", password)
                        .param("groupName", groupName))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testUpdateStudent() throws Exception {
        // User data before test
        UserDto userBeforeTest = userService.getUserById(userId);

        // Prepare data to replace in database
        String expectedFirstname = "newFirstname";
        String expectedLastname = "newLastname";
        Long expectedGroupId = 4L;

        // Confirm what data to update is different from actual
        assertNotEquals(expectedFirstname, userBeforeTest.getFirstname());
        assertNotEquals(expectedLastname, userBeforeTest.getLastname());
        assertNotEquals(expectedGroupId, userBeforeTest.getOwnerGroup().getName());

        mockMvc.perform(post("/user/updateStudent")
                        .param("id", String.valueOf(userId))
                        .param("firstname", expectedFirstname)
                        .param("lastname", expectedLastname)
                        .param("ownerGroup.id", String.valueOf(expectedGroupId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/student-management"));

        //Retreiving a user database after modification
        UserDto actualUser = userService.getUserById(userId);

        // Verify that Student data was updated in the database
        assertEquals(expectedFirstname, actualUser.getFirstname());
        assertEquals(expectedLastname, actualUser.getLastname());
        assertEquals(expectedGroupId, actualUser.getOwnerGroup().getId());

        // Revert changes
        actualUser.setFirstname("alex");
        actualUser.setLastname("Collier");
        actualUser.setOwnerGroup(groupService.getGroupById(1));
        userService.save(actualUser);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteStudent_AdminAccess() throws Exception {
        // Create a test Student to be deleted
        User testUser = new User();
        testUser.setFirstname("TestStd");
        testUser.setLastname("TestStd");
        testUser.setLogin("teststd2");
        testUser.setPassword("test");
        testUser.setOwnerGroup(groupService.getGroupById(9L));
        userService.save(testUser);

        // Verify that Student was saved to database
        assertTrue(userService.existsByLogin(testUser.getLogin()));

        mockMvc.perform(post("/user/deleteStudent")
                        .param("userId", String.valueOf(testUser.getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/student-management"))
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(flash().attribute("successMessage", "Student deleted successfully!"));

        // Verify that Student was deleted from the database
        assertFalse(userService.existsByLogin(testUser.getLogin()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteStudentError() throws Exception {
        long nonExistingUserId = 77L;

        // Verify that Student does not exist in database
        assertFalse(userService.existsById(nonExistingUserId));

        mockMvc.perform(post("/user/deleteStudent")
                        .param("userId", String.valueOf(nonExistingUserId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/student-management"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Student not found or could not be deleted"));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testDeleteStudent_StudentAccess() throws Exception {
        Long studentIdToDelete = 40L;

        mockMvc.perform(post("/user/deleteStudent")
                        .param("userId", String.valueOf(studentIdToDelete)))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateStudentCredentials_AdminAccess() throws Exception {
        // Prepare the updated data
        String expectedLogin = "newLogin";
        String expectedPassword = "newPass";

        // Confirm what data to update is different from actual
        assertNotEquals(expectedLogin, userService.getUserById(userId).getLogin());
        assertFalse(passwordEncoder.matches(expectedPassword, userService.getUserById(userId).getPassword()));

        mockMvc.perform(post("/user/updateCredentials")
                        .param("id", String.valueOf(userId))
                        .param("login", expectedLogin)
                        .param("password", expectedPassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/student-management"))
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(flash().attribute("successMessage", "Credential modification was successful!"));

        //Retreiving a user database after modification
        UserDto actualUser = userService.getUserById(userId);
        String actualLogin = actualUser.getLogin();
        String actualPassword = actualUser.getPassword();

        // Verify that Credentials was modificated
        assertEquals(expectedLogin, actualLogin);
        assertTrue(passwordEncoder.matches(expectedPassword, actualPassword));

        // Revert changes
        actualUser.setLogin("test1");
        actualUser.setPassword(passwordEncoder.encode("pass"));
        userService.save(actualUser);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateStudentCredentialsError() throws Exception {
        // Using existing data
        String existingLogin = userService.getUserById(1).getLogin();
        String somePassword = "somePassword";

        mockMvc.perform(post("/user/updateCredentials")
                        .param("id", String.valueOf(userId))
                        .param("login", existingLogin)
                        .param("password", somePassword))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/student-management"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Account with this login already exists"));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testUpdateStudentCredentials_StudentAccess() throws Exception {
        // Prepare the updated data
        String expectedLogin = "newLogin";
        String expectedPassword = "newPass";

        mockMvc.perform(post("/user/updateCredentials")
                        .param("userId", String.valueOf(userId))
                        .param("login", expectedLogin)
                        .param("password", expectedPassword))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }

    @WithUserDetails(value = "teach1")
    @Test
    void getUsersByGroupId_Controller_ShouldReturnListOfUsers() throws Exception {
        mockMvc.perform(get("/user/{groupId}", groupId))
                .andExpect(status().isOk())
                .andExpect(view().name("db/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(3)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("firstname", is("admin"))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("firstname", is("alex")),
                                hasProperty("lastname", is("Collier"))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("firstname", is("John")),
                                hasProperty("lastname", is("Collier"))
                        )
                )));
    }
}
