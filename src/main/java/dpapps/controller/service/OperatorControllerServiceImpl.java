package dpapps.controller.service;

import dpapps.constants.RoleConstants;
import dpapps.model.Project;
import dpapps.model.Task;
import dpapps.model.repository.service.CodingLanguageService;
import dpapps.model.repository.service.ProjectService;
import dpapps.model.repository.service.TaskService;
import dpapps.model.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class OperatorControllerServiceImpl implements OperatorControllerService{

    private final ProjectService projectService;

    private final CodingLanguageService codingLanguageService;

    private final UserService userService;

    private final TaskService taskService;

    @Autowired
    public OperatorControllerServiceImpl(ProjectService projectService, CodingLanguageService codingLanguageService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.codingLanguageService = codingLanguageService;
        this.userService = userService;
        this.taskService = taskService;
    }


    @Override
    public String getPanel() {
        return "/operator/panel";
    }

    @Override
    public String getAddTaskView(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("languages", codingLanguageService.findAll());
        model.addAttribute("users", userService.findAllByRole(RoleConstants.ROLE_DESIGNER));
        return "/operator/add-task";
    }

    @Override
    public String saveTask(Task task, RedirectAttributes redirectAttributes) {
        boolean isTaskAdded = this.taskService.add(task);
        if (isTaskAdded) {
            redirectAttributes.addFlashAttribute("successMessage", "Task added successfully!");
            return "redirect:/operator/add-task?success";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding task. Please try again.");
            return "redirect:/operator/add-task?error";
        }
    }
}
