package dpapps.controller.service;

import dpapps.constants.RoleConstants;
import dpapps.controller.service.templateservice.OperatorTemplateService;
import dpapps.model.*;
import dpapps.model.repository.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperatorControllerServiceImpl implements OperatorControllerService{

    private final ProjectService projectService;

    private final CodingLanguageService codingLanguageService;

    private final UserService userService;

    private final TaskService taskService;

    private final OperatorTemplateService operatorTemplateService;

    private final TaskNotificationService taskNotificationService;

    private final ArchivedTaskNotificationService archivedTaskNotificationService;

    private final ArchivedTaskService archivedTaskService;

    private final TaskReviewService taskReviewService;

    public OperatorControllerServiceImpl(ProjectService projectService, CodingLanguageService codingLanguageService, UserService userService, TaskService taskService, OperatorTemplateService operatorTemplateService, TaskNotificationService taskNotificationService, ArchivedTaskNotificationService archivedTaskNotificationService, ArchivedTaskService archivedTaskService, TaskReviewService taskReviewService) {
        this.projectService = projectService;
        this.codingLanguageService = codingLanguageService;
        this.userService = userService;
        this.taskService = taskService;
        this.operatorTemplateService = operatorTemplateService;
        this.taskNotificationService = taskNotificationService;
        this.archivedTaskNotificationService = archivedTaskNotificationService;
        this.archivedTaskService = archivedTaskService;
        this.taskReviewService = taskReviewService;
    }


    @Override
    public String getPanel(Model model) {
        List<ArchivedTaskNotification> archivedTaskNotifications = archivedTaskNotificationService.findAll();
        model.addAttribute("archivedTaskNotifications", archivedTaskNotifications);
        archivedTaskNotificationService.removeAll();
        return operatorTemplateService.getOperatorPanelView(model);
    }

    @Override
    public String getAddTaskView(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("languages", codingLanguageService.findAll());
        model.addAttribute("users", userService.findAllByRole(RoleConstants.ROLE_DESIGNER));
        return operatorTemplateService.getNewTaskView(model);
    }

    @Override
    public String saveTask(Task task, RedirectAttributes redirectAttributes) {
        boolean isTaskAdded = taskService.save(task);
        if (isTaskAdded) {
            notifyUser(task);
            redirectAttributes.addFlashAttribute("successMessage", "Task added successfully!");
            return operatorTemplateService.getSuccessfulTaskCreationView(redirectAttributes);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding task. Please try again.");
            return operatorTemplateService.getUnsuccessfulTaskCreationView(redirectAttributes);
        }
    }

    @Override
    public String getTasksList(Model model) {
        List<Task> allTasks = taskService.findAll();
        model.addAttribute("tasks", allTasks);
        return operatorTemplateService.getTasksList(model);
    }

    @Override
    public String getTasksList(int page,
                               int size,
                               String sortField,
                               String sortOrder,
                               String userFilter,
                               String projectFilter,
                               String languageFilter,
                               Model model) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortField));
        Page<Task> tasks = taskService.getAllTasks(pageRequest, userFilter, projectFilter, languageFilter);
        model.addAttribute("tasks", tasks.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tasks.getTotalPages());
        model.addAttribute("totalItems", tasks.getTotalElements());
        return operatorTemplateService.getTasksList(model);
    }

    @Override
    public String getEditTask(Long id, RedirectAttributes redirectAttributes, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("languages", codingLanguageService.findAll());
        model.addAttribute("users", userService.findAllByRole(RoleConstants.ROLE_DESIGNER));
        return operatorTemplateService.getEditTaskView(model);
    }

    @Override
    public String saveEditedTask(Task task, RedirectAttributes redirectAttributes) {
        boolean isTaskAdded = taskService.save(task);
        if (isTaskAdded) {
            redirectAttributes.addFlashAttribute("successMessage", "Task edited successfully!");
            return operatorTemplateService.getSuccessfulTaskEditView(redirectAttributes, task.getId());
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Could not edit task. Please try again.");
            return operatorTemplateService.getUnsuccessfulTaskEditView(redirectAttributes, task.getId());
        }
    }

    @Override
    public String getReview(Long id, Model model) {
        TaskReview taskReview = new TaskReview();
        ArchivedTask archivedTask = archivedTaskService.findTaskById(id);
        archivedTask.setReview(taskReview);
        model.addAttribute("archivedTask", archivedTask);
        return operatorTemplateService.getReviewView(model);
    }

    @Override
    public String getArchived(Model model) {
        List<ArchivedTask> archivedTasks = archivedTaskService.findAll();
        model.addAttribute("archivedTasks", archivedTasks);
        return operatorTemplateService.getArchived(model);
    }

    @Override
    public String saveReview(ArchivedTask archivedTask) {
        ArchivedTask existingTask = archivedTaskService.findTaskById(archivedTask.getId());
        existingTask.setReview(archivedTask.getReview());
        taskReviewService.save(archivedTask.getReview());
        archivedTaskService.save(existingTask);
        return operatorTemplateService.getReviewSubmitted();
    }

    private void notifyUser(Task task) {
        TaskNotification taskNotification = taskNotificationService.prepareNotification(task);
        taskNotificationService.save(taskNotification);
    }
}
