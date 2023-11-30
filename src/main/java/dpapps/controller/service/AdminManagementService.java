package dpapps.controller.service;

import dpapps.exception.UserCouldNotBeSavedInTheDatabaseException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface AdminManagementService {

    /**
     * Returns view where admin can assign user roles
     */
    String getUserManagement(Model model);

    /**
     * Update user roles
     */
    String assignRoles(Long userId, List<Long> selectedRoles, RedirectAttributes redirectAttributes);

    /**
     * Renders admin panel
     */
    String getAdminPanel();
}
