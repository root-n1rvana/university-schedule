package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.javaspring.kocherga.web_application.models.Group;
import ua.foxminded.javaspring.kocherga.web_application.models.dto.GroupDto;
import ua.foxminded.javaspring.kocherga.web_application.repository.GroupRepository;
import ua.foxminded.javaspring.kocherga.web_application.service.impl.GroupServiceImpl;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    private GroupRepository groupRepository;

    @WithMockUser("spring")
    @Test
    public void testGroupPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/group/management"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("management/group-management"))
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attribute("groups", hasSize(8)))
                .andExpect(model().attribute("groups", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("name", is("professor"))
                        )
                )));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateGroup() throws Exception {
        // Create a test group
        Group testGroup = new Group();
        testGroup.setName("Test Group");
        groupRepository.save(testGroup);
        GroupDto savedGroup = groupService.getGroupDtoById(testGroup.getId());

        // Prepare the updated data
        String expectedGroupName = "New Name";

        mockMvc.perform(post("/group/update")
                        .param("id", String.valueOf(savedGroup.getId()))
                        .param("name", expectedGroupName))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/group/management"));

        // Verify that the group was updated in the database
        GroupDto actualGroup = groupService.getGroupDtoById(savedGroup.getId());
        assertEquals(expectedGroupName, actualGroup.getName());

        // Cleaning after test
        groupRepository.deleteByName("New Name");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateGroup_Error() throws Exception {
        Long existingGroupId = 3L;

        // Prepare the updated data
        String expectedGroupName = "professor";

        mockMvc.perform(post("/group/update")
                        .param("id", String.valueOf(existingGroupId))
                        .param("name", expectedGroupName))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/group/management"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Group with the same name already exists."));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void testAddGroup_ProfessorAccess() throws Exception {
        String newGroupName = "New Course";

        mockMvc.perform(post("/group/addGroup")
                        .param("newGroupName", newGroupName))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/group/management"))
                .andExpect(flash().attributeExists("successMessage"))
                .andExpect(flash().attribute("successMessage", "Group added successfully!"));

        // Verify that the group was added to the database
        assertTrue(groupRepository.existsByName(newGroupName));

        // Cleaning after test
        groupRepository.deleteByName(newGroupName);
        assertFalse(groupRepository.existsByName(newGroupName));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddGroupError() throws Exception {
        String newGroupName = "admin";

        // Verify that the group already exists
        assertTrue(groupRepository.existsByName(newGroupName));

        mockMvc.perform(post("/group/addGroup")
                        .param("newGroupName", newGroupName))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/group/management"))
                .andExpect(flash().attributeExists("errorMessage"))
                .andExpect(flash().attribute("errorMessage", "Group with the same name already exists."));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testAddGroup_StudentAccess() throws Exception {
        String newGroupName = "New Group";

        mockMvc.perform(post("/group/addGroup")
                        .param("newGroupName", newGroupName))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteGroup_AdminAccess() throws Exception {
        // Create a test group to be deleted
        Group testGroup = new Group();
        testGroup.setName("ToDelete");
        groupRepository.save(testGroup);

        // Verify that the group was saved to database
        assertTrue(groupRepository.existsByName(testGroup.getName()));

        mockMvc.perform(post("/group/delete")
                        .param("groupId", String.valueOf(testGroup.getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/group/management"))
                .andExpect(flash().attributeExists("deletionSucceeded"))
                .andExpect(flash().attribute("deletionSucceeded", "Group deleted successfully!"));

        // Verify that the group was deleted from the database
        assertFalse(groupRepository.existsByName(testGroup.getName()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteGroupError() throws Exception {
        long nonExistingGroupId = 77L;

        // Verify that Student does not exist in database
        assertFalse(groupService.existByGroupId(nonExistingGroupId));

        mockMvc.perform(post("/group/delete")
                        .param("groupId", String.valueOf(nonExistingGroupId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/group/management"))
                .andExpect(flash().attributeExists("deletionError"))
                .andExpect(flash().attribute("deletionError", "Course not found or could not be deleted"));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testDeleteGroup_StudentAccess() throws Exception {
        String groupNameToDelete = "ToDelete";

        mockMvc.perform(post("/group/delete")
                        .param("ToDelete", groupNameToDelete))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden response
    }
}