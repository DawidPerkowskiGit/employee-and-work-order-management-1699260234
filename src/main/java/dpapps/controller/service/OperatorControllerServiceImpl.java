package dpapps.controller.service;

import dpapps.constants.RoleConstants;
import dpapps.controller.service.templateservice.OperatorTemplateService;
import dpapps.model.Task;
import dpapps.model.TaskNotification;
import dpapps.model.repository.service.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class OperatorControllerServiceImpl implements OperatorControllerService{

    private final ProjectService projectService;

    private final CodingLanguageService codingLanguageService;

    private final UserService userService;

    private final TaskService taskService;

    private final OperatorTemplateService templateService;

    private final TaskNotificationService taskNotificationService;

    public OperatorControllerServiceImpl(ProjectService projectService, CodingLanguageService codingLanguageService, UserService userService, TaskService taskService, OperatorTemplateService templateService, TaskNotificationService taskNotificationService) {
        this.projectService = projectService;
        this.codingLanguageService = codingLanguageService;
        this.userService = userService;
        this.taskService = taskService;
        this.templateService = templateService;
        this.taskNotificationService = taskNotificationService;
    }


    @Override
    public String getPanel() {
        return templateService.getOperatorPanelView();
    }

    @Override
    public String getAddTaskView(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("languages", codingLanguageService.findAll());
        model.addAttribute("users", userService.findAllByRole(RoleConstants.ROLE_DESIGNER));
        return templateService.getNewTaskView(model);
    }

    @Override
    public String saveTask(Task task, RedirectAttributes redirectAttributes) {
        boolean isTaskAdded = taskService.save(task);
        if (isTaskAdded) {
            notifyUser(task);
            redirectAttributes.addFlashAttribute("successMessage", "Task added successfully!");
            return templateService.getSuccessfulTaskCreationView(redirectAttributes);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding task. Please try again.");
            return templateService.getUnsuccessfulTaskCreationView(redirectAttributes);
        }
    }

    @Override
    public String getTasksList(Model model) {
        List<Task> allTasks = taskService.findAll();
        model.addAttribute("tasks", allTasks);
        return templateService.getTasksList(model);
    }

    @Override
    public String getEditTask(Long id, RedirectAttributes redirectAttributes, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("languages", codingLanguageService.findAll());
        model.addAttribute("users", userService.findAllByRole(RoleConstants.ROLE_DESIGNER));
        return templateService.getEditTaskView(model);
    }

    @Override
    public String saveEditedTask(Task task, RedirectAttributes redirectAttributes) {
        boolean isTaskAdded = taskService.save(task);
        if (isTaskAdded) {
            redirectAttributes.addFlashAttribute("successMessage", "Task edited successfully!");
            return templateService.getSuccessfulTaskEditView(redirectAttributes, task.getId());
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Could not edit task. Please try again.");
            return templateService.getUnsuccessfulTaskEditView(redirectAttributes, task.getId());
        }
    }
    
    private void notifyUser(Task task) {
        TaskNotification taskNotification = taskNotificationService.prepareNotification(task);
        taskNotificationService.save(taskNotification);
    }
}
