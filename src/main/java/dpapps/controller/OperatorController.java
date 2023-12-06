package dpapps.controller;

import dpapps.controller.service.OperatorControllerService;
import dpapps.model.ArchivedTask;
import dpapps.model.Task;
import dpapps.model.TaskNotification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OperatorController {

    private final OperatorControllerService controllerService;


    public OperatorController(OperatorControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/operator/panel")
    public String renerPanel(Model model) {
        return controllerService.getPanel(model);
    }

    @GetMapping("/operator/add-task")
    public String renerAddTask(Model model) {
        return controllerService.getAddTaskView(model);
    }

    @PostMapping("/operator/add-task")
    public String processAddTask(Task task, RedirectAttributes redirectAttributes) {
        return controllerService.saveTask(task, redirectAttributes);
    }

    @GetMapping("/operator/tasks/edit/{taskId}")
    public String editTask(@PathVariable Long taskId, RedirectAttributes redirectAttributes, Model model) {
        return controllerService.getEditTask(taskId, redirectAttributes, model);
    }

    @PostMapping("/operator/tasks/edit/save")
    public String saveEditedTask(Task task, RedirectAttributes redirectAttributes) {
        return controllerService.saveEditedTask(task, redirectAttributes);
    }

    @GetMapping("/operator/archived")
    public String getArchivedList(Model model) {
        return controllerService.getArchived(model);
    }



    @GetMapping("/operator/archived/review/{id}")
    public String getReviewView(@PathVariable Long id, Model model) {
        return controllerService.getReview(id, model);
    }

    @PostMapping("/operator/archived/review/submit")
    public String submitReview(ArchivedTask archivedTask) {
        return controllerService.saveReview(archivedTask);
    }

    @GetMapping("/operator/tasks")
    public String getTasksLists(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortField,
                                @RequestParam(defaultValue = "asc") String sortOrder,
                                @RequestParam(defaultValue = "") String userFilter,
                                @RequestParam(defaultValue = "") String projectFilter,
                                @RequestParam(defaultValue = "") String languageFilter,
                                Model model) {
        return controllerService.getTasksList(page, size, sortField, sortOrder, userFilter, projectFilter, languageFilter, model);
    }
}
