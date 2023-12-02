package dpapps.controller;

import dpapps.controller.service.OperatorControllerService;
import dpapps.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OperatorController {

    private final OperatorControllerService controllerService;

    public OperatorController(OperatorControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/operator/panel")
    public String renerPanel() {
        return controllerService.getPanel();
    }

    @GetMapping("/operator/add-task")
    public String renerAddTask(Model model) {
        return controllerService.getAddTaskView(model);
    }

    @PostMapping("/operator/add-task")
    public String processAddTask(Task task, RedirectAttributes redirectAttributes) {
        return controllerService.saveTask(task, redirectAttributes);
    }

    @GetMapping("/operator/tasks")
    public String getTasksLists(Model model) {
        return controllerService.getTasksList(model);
    }

    @GetMapping("/operator/tasks/edit/{taskId}")
    public String editTask(@PathVariable Long taskId, RedirectAttributes redirectAttributes, Model model) {
        return controllerService.getEditTask(taskId, redirectAttributes, model);
    }

    @PostMapping("/operator/tasks/edit/save")
    public String saveEditedTask(Task task, RedirectAttributes redirectAttributes) {
        return controllerService.saveEditedTask(task, redirectAttributes);
    }
}
