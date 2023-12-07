package dpapps.controller;

import dpapps.controller.service.WorkTimeManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkTimeController {

    private final WorkTimeManagementService workTimeManagementService;

    public WorkTimeController(WorkTimeManagementService workTimeManagementService) {
        this.workTimeManagementService = workTimeManagementService;
    }

    @GetMapping("/worktime")
    public String getWorkTimePanel(Model model) {
        return workTimeManagementService.getPanel(model);
    }
}
