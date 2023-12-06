package dpapps.controller;

import dpapps.controller.service.DesignerControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DesignerController {

    private final DesignerControllerService controllerService;


    public DesignerController(DesignerControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/designer/panel")
    public String getPanel(Model model) {
        return controllerService.getPanel(model);
    }


    @GetMapping("/designer/tasks/details/{id}")
    public String getTasks(@PathVariable Long id, Model model) {
        return controllerService.getTaskDetails(model, id);
    }

    @PostMapping("/designer/tasks/complete/{id}")
    public String completeTasks(@PathVariable Long id) {
        return controllerService.completeTask(id);
    }

    @GetMapping("/designer/archived")
    public String getCompletedTasks(Model model) {
        return controllerService.getCompletedTasks(model);
    }

    @GetMapping("/designer/tasks")
    public String getTasks(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortField,
                                @RequestParam(defaultValue = "asc") String sortOrder,
                                @RequestParam(defaultValue = "") String userFilter,
                                @RequestParam(defaultValue = "") String projectFilter,
                                @RequestParam(defaultValue = "") String languageFilter,
                                Model model) {
        return controllerService.getTasks(page, size, sortField, sortOrder, userFilter, projectFilter, languageFilter, model);
    }
}
