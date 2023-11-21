package dpapps.controller;

import dpapps.controller.service.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class AdminController {

    private final AdminManagementService controllerService;

    @Autowired
    public AdminController(AdminManagementService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/admin/dashboard")
    public String getAdminDashboard(Model model) {
        return controllerService.listUsers(model);
    }
}
