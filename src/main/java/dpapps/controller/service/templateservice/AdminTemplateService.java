package dpapps.controller.service.templateservice;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface AdminTemplateService {
    /**
     * Return admin only, user management view
     */
    String getUserManagementView(Model model);

    /**
     * Return user management view after successfully processing roles
     */
    String getAssignRolesSuccessfulView(Long userId, List<Long> selectedRoles, RedirectAttributes redirectAttributes);

    /**
     * Return user management view after unsuccessfully processing roles
     */
    String getAssignRolesUnsuccessfulView(Long userId, List<Long> selectedRoles, RedirectAttributes redirectAttributes);

    /**
     * Returns admin panel view
     */
    String getAdminPanel();
}
