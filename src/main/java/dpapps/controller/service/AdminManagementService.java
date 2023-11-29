package dpapps.controller.service;

import org.springframework.ui.Model;

import java.util.List;

public interface AdminManagementService {

    /**
     * Returns view where admin can assign user roles
     */
    String getUserManagement(Model model);

    /**
     * Update user roles
     */
    String assignRoles(Long userId, List<Long> selectedRoles);

    /**
     * Renders admin panel
     */
    String getAdminPanel();
}
