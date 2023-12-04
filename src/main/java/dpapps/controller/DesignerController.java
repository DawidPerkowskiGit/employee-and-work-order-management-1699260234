package dpapps.controller;

import dpapps.controller.service.DesignerControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/designer/tasks")
    public String getTasks(Model model) {
        return controllerService.getTasks(model);
    }

    @GetMapping("/designer/tasks/details/{id}")
    public String getTasks(@PathVariable Long id, Model model) {
        return controllerService.getTaskDetails(model, id);
    }

    @PostMapping("/designer/tasks/complete/{id}")
    public String completeTasks(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        return controllerService.completeTask(id, redirectAttributes);
    }
}
