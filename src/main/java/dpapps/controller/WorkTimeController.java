package dpapps.controller;

import dpapps.controller.service.WorkTimeManagementService;
import dpapps.model.repository.RoleRepository;
import dpapps.model.repository.WorkingLogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WorkTimeController {

    private final WorkTimeManagementService workTimeManagementService;
    private final RoleRepository roleRepository;
    private final WorkingLogRepository workingLogRepository;

    public WorkTimeController(WorkTimeManagementService workTimeManagementService,
                              RoleRepository roleRepository,
                              WorkingLogRepository workingLogRepository) {
        this.workTimeManagementService = workTimeManagementService;
        this.roleRepository = roleRepository;
        this.workingLogRepository = workingLogRepository;
    }

    @GetMapping("/worktime")
    public String getWorkTimePanel(Model model) {
        return workTimeManagementService.getPanel(model);
    }

    @PostMapping("/worktime/start")
    public String startWork(RedirectAttributes redirectAttributes) {
        return workTimeManagementService.startWork(redirectAttributes);
    }

    @PostMapping("/worktime/stop")
    public String stopWork(RedirectAttributes redirectAttributes) {
        return workTimeManagementService.stopWork(redirectAttributes);
    }

}
