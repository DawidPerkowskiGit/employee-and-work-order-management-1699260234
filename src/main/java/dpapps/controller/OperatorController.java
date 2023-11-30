package dpapps.controller;

import dpapps.controller.service.OperatorControllerService;
import dpapps.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return this.controllerService.getPanel();
    }

    @GetMapping("/operator/add-task")
    public String renerAddTask(Model model) {
        return this.controllerService.getAddTaskView(model);
    }

    @PostMapping("/operator/add-task")
    public String processAddTask(Task task, RedirectAttributes redirectAttributes) {
        return this.controllerService.saveTask(task, redirectAttributes);
    }


}
