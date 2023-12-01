package dpapps.task;

import dpapps.model.Task;
import dpapps.model.repository.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskTests {

    private final TaskService taskService;
    private final String testTaskName = "Test Task";
    private final String testTaskDescription = "Test Description";
    private final String paramName = "name";
    private final String paramDescription = "description";
    private final String paramProjectId = "project.id";
    private final String paramCodingLanguageId = "codingLanguage.id";
    private final String paramUserId = "user.id";
    private final String validProjectId = "1";
    private final String invalidProjectId = "10000000";
    private final String validCodingLanguageId = "1";
    private final String invalidCodingLanguageId = "10000000";
    private final String validUserId = "1";
    private final String invalidUserId = "10000000";
    private final String modifiedDescription = "Modified Test Description";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public TaskTests(TaskService taskService) {
        this.taskService = taskService;
    }


    @Test
    @WithMockUser(username = "testUser", roles = "OPERATOR")
    public void testSaveTaskSuccess() throws Exception {

        mockMvc.perform(post("/operator/add-task")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(paramName, testTaskName)
                        .param(paramDescription, testTaskDescription)
                        .param(paramProjectId, validProjectId)
                        .param(paramCodingLanguageId, validCodingLanguageId)
                        .param(paramUserId, validUserId)
                )
                .andExpect(redirectedUrl("/operator/add-task?success"));

        this.cleanupTestUsers();
    }

    @Test
    @WithMockUser(username = "testUser", roles = "OPERATOR")
    public void testSaveTaskFailureInvalidProject() throws Exception {

        mockMvc.perform(post("/operator/add-task")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(paramName, testTaskName)
                        .param(paramDescription, testTaskDescription)
                        .param(paramProjectId, invalidProjectId)
                        .param(paramCodingLanguageId, validCodingLanguageId)
                        .param(paramUserId, validUserId)
                )
                .andExpect(redirectedUrl("/operator/add-task?error"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "OPERATOR")
    public void testSaveTaskFailureInvalidCodingLanguage() throws Exception {

        mockMvc.perform(post("/operator/add-task")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(paramName, testTaskName)
                        .param(paramDescription, testTaskDescription)
                        .param(paramProjectId, validProjectId)
                        .param(paramCodingLanguageId, invalidCodingLanguageId)
                        .param(paramUserId, validUserId)
                )
                .andExpect(redirectedUrl("/operator/add-task?error"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "OPERATOR")
    public void testSaveTaskFailureInvalidUser() throws Exception {

        mockMvc.perform(post("/operator/add-task")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(paramName, testTaskName)
                        .param(paramDescription, testTaskDescription)
                        .param(paramProjectId, validProjectId)
                        .param(paramCodingLanguageId, validCodingLanguageId)
                        .param(paramUserId, invalidUserId)
                )
                .andExpect(redirectedUrl("/operator/add-task?error"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    public void testSaveTaskFailureAdminRole() throws Exception {

        mockMvc.perform(post("/operator/add-task")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(paramName, testTaskName)
                        .param(paramDescription, testTaskDescription)
                        .param(paramProjectId, validProjectId)
                        .param(paramCodingLanguageId, validCodingLanguageId)
                        .param(paramUserId, validUserId)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "DESIGNER")
    public void testSaveTaskFailureDesignerRole() throws Exception {

        mockMvc.perform(post("/operator/add-task")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(paramName, testTaskName)
                        .param(paramDescription, testTaskDescription)
                        .param(paramProjectId, validProjectId)
                        .param(paramCodingLanguageId, validCodingLanguageId)
                        .param(paramUserId, validUserId)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "OPERATOR")
    public void testEditTaskSuccess() throws Exception {

        mockMvc.perform(post("/operator/add-task")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(paramName, testTaskName)
                .param(paramDescription, testTaskDescription)
                .param(paramProjectId, validProjectId)
                .param(paramCodingLanguageId, validCodingLanguageId)
                .param(paramUserId, validUserId)
        );

        Task initialTask = taskService.findByName(testTaskName);
        Long initialTaskId = initialTask.getId();

        mockMvc.perform(post("/operator/tasks/edit/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "" + initialTaskId)
                .param(paramName, testTaskName)
                .param(paramDescription, modifiedDescription)
                .param(paramProjectId, validProjectId)
                .param(paramCodingLanguageId, validCodingLanguageId)
                .param(paramUserId, validUserId)
        ).andExpect(redirectedUrl("/operator/tasks/edit/" + initialTaskId + "?success"));

        Task editedTask = taskService.findByName(testTaskName);

        this.cleanupTestUsers();

        assertTrue(!initialTask.equals(editedTask) && initialTask.getDescription().equals(testTaskDescription) && editedTask.getDescription().equals(modifiedDescription));

    }

    private void cleanupTestUsers() {
        taskService.deleteAllByName(testTaskName);
    }
}
