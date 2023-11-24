package dpapps.controller;

import dpapps.controller.service.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
public class AdminController {

    private final AdminManagementService controllerService;


    @Autowired
    public AdminController(AdminManagementService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping("/admin/user-management")
    public String getAdminDashboard(Model model) {
        return controllerService.listUsers(model);
    }

    @PostMapping("/admin/assignRoles")
    public String assignRoles(@RequestParam Long userId, @RequestParam List<Long> selectedRoles) {
        return controllerService.assignRoles(userId, selectedRoles);
    }
}
