package dpapps.task;

import dpapps.model.*;
import dpapps.model.repository.*;
import dpapps.model.repository.service.TaskNotificationService;
import dpapps.model.repository.service.TaskService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskTests {

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


    private final TaskService taskService;

    private final TaskNotificationService taskNotificationService;

    private final TaskRepository taskRepository;


    private final ProjectRepository projectRepository;

    private final CodingLanguageRepository codingLanguageRepository;

    private final UserRepository userRepository;

    private final ArchivedTaskRepository archivedTaskRepository;
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    public TaskTests(TaskService taskService, TaskNotificationService taskNotificationService, TaskRepository taskRepository, ProjectRepository projectRepository, CodingLanguageRepository codingLanguageRepository, UserRepository userRepository, ArchivedTaskRepository archivedTaskRepository) {
        this.taskService = taskService;
        this.taskNotificationService = taskNotificationService;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.codingLanguageRepository = codingLanguageRepository;
        this.userRepository = userRepository;
        this.archivedTaskRepository = archivedTaskRepository;
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

        cleanupTestTasks();
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

        addNewTask();

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

        cleanupTestTasks();

        assertTrue(!initialTask.equals(editedTask) &&
                initialTask.getDescription().equals(testTaskDescription) &&
                editedTask.getDescription().equals(modifiedDescription));

    }

    @Test
    @WithMockUser(username = "testUser", roles = "OPERATOR")
    public void testTaskNotification() throws Exception {

        addNewTask();

        Task task = taskService.findByName(testTaskName);

        TaskNotification taskNotification = taskNotificationService.getNotificationByTask(task);

        cleanupTestTasks();
        assertTrue(taskNotification.isRequiresNotification());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "DESIGNER")
    public void testShouldCompleteTask() throws Exception {


        addNewTask();

        Task initialTask = taskService.findByName(testTaskName);

        mockMvc.perform(post("/designer/tasks/complete/" + initialTask.getId()))
                .andExpect(redirectedUrl("/designer/tasks?taskCompleted"));

        ArchivedTask archivedTask = archivedTaskRepository.findByName(testTaskName).get();

        assertTrue(initialTask.isCompleted() == false && archivedTask.isCompleted());

        cleanupTestTasks();

    }

    @Disabled
    @Test
    public void turnOnNotifications() {
        taskNotificationService.setNotificationsNeededToDisplay(3L);
    }

    @Test
    public void cleanupTestTasks() {
        taskService.deleteAllByName(testTaskName);
    }

    private void addNewTask() {
        Task newTask = new Task();
        Project project = projectRepository.findById(1L).get();
        CodingLanguage codingLanguage = codingLanguageRepository.findById(1L).get();
        User user = userRepository.findById(1L).get();


        newTask.setName(testTaskName);
        newTask.setDescription(testTaskDescription);
        newTask.setProject(new Project());
        newTask.setUser(user);
        newTask.setCodingLanguage(codingLanguage);
        newTask.setProject(project);

        taskRepository.save(newTask);
    }


}
