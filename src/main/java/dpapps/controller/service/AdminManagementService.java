package dpapps.controller.service;

import org.springframework.ui.Model;

import java.util.List;

public interface AdminManagementService {
    String listUsers(Model model);

    String assignRoles(Long userId, List<Long> selectedRoles);
}
