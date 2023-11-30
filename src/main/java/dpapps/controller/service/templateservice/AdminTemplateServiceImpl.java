package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class AdminTemplateServiceImpl implements AdminTemplateService{
    @Override
    public String getUserManagementView(Model model) {
        return "admin/user-management";
    }

    @Override
    public String getAssignRolesSuccessfulView(Long userId, List<Long> selectedRoles, RedirectAttributes redirectAttributes) {
        return "redirect:/admin/user-management?success";
    }

    @Override
    public String getAssignRolesUnsuccessfulView(Long userId, List<Long> selectedRoles, RedirectAttributes redirectAttributes) {
        return "redirect:/admin/user-management?error";
    }

    @Override
    public String getAdminPanel() {
        return "admin/panel";
    }
}
