package dpapps.controller.service;

import dpapps.controller.service.templateservice.DesignerTemplateService;
import dpapps.controller.service.templateservice.ErrorTemplateService;
import dpapps.model.ArchivedTask;
import dpapps.model.Task;
import dpapps.model.TaskNotification;
import dpapps.model.User;
import dpapps.model.repository.service.ArchivedTaskService;
import dpapps.model.repository.service.TaskNotificationService;
import dpapps.model.repository.service.TaskService;
import dpapps.model.repository.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesignerControllerServiceImpl implements DesignerControllerService{

    private final DesignerTemplateService designerTemplateService;

    private final TaskNotificationService taskNotificationService;

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ErrorTemplateService errorTemplateService;

    private final TaskService taskService;

    private final ArchivedTaskService archivedTaskService;

    public DesignerControllerServiceImpl(DesignerTemplateService designerTemplateService, TaskNotificationService taskNotificationService, UserService userService, ErrorTemplateService errorTemplateService, TaskService taskService, ArchivedTaskService archivedTaskService) {
        this.designerTemplateService = designerTemplateService;
        this.taskNotificationService = taskNotificationService;
        this.userService = userService;
        this.errorTemplateService = errorTemplateService;
        this.taskService = taskService;
        this.archivedTaskService = archivedTaskService;
    }

    @Override
    public String getPanel(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        }
        catch (Exception e) {
            logger.warn("Could not find authenticated user");
            return errorTemplateService.getNotFoundView();
        }

        Long userId = user.getId();
        List<TaskNotification> notifications = taskNotificationService.getNotifications(userId);
        model.addAttribute("notifications", notifications);
        if (!notifications.isEmpty()) {
            taskNotificationService.deleteNotifications(userId);
        }

        return designerTemplateService.getDesignerPanelView(model);
    }

    @Override
    public String getTasks(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        }
        catch (Exception e) {
            logger.warn("Could not find User");
            return errorTemplateService.getNotFoundView();
        }
        List<Task> allTasks = taskService.findAllByUser(user);
        model.addAttribute("tasks", allTasks);
        List<Task> filteredTasks = new ArrayList<>();
        model.addAttribute("filteredTasks", filteredTasks);
        return designerTemplateService.getTasksList(model);
    }

    @Override
    public String getTaskDetails(Model model, Long id) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return designerTemplateService.getTaskDetails(model);
    }

    @Override
    public String completeTask(Long id) {
        taskService.setCompleted(id);
        return designerTemplateService.getSuccessfulTaskCompletion();
    }

    @Override
    public String getCompletedTasks(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        }
        catch (Exception e) {
            logger.warn("User not found");
            return errorTemplateService.getNotFoundView();
        }

        List<ArchivedTask> archivedTasks = archivedTaskService.findAllByDesigner(user);
        model.addAttribute("archivedTasks", archivedTasks);
        return designerTemplateService.getCompletedTasks(model);
    }
}
