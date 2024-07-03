package dpapps.controller.service;

import dpapps.controller.service.templateservice.AdminTemplateService;
import dpapps.exception.UserCouldNotBeSavedInTheDatabaseException;
import dpapps.model.Role;
import dpapps.model.User;
import dpapps.model.repository.service.RoleService;
import dpapps.model.repository.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminManagementServiceImpl implements AdminManagementService {

    private final UserService userService;

    private final RoleService roleService;

    private final AdminTemplateService templateService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public AdminManagementServiceImpl(UserService userService, RoleService roleService, AdminTemplateService templateService) {
        this.userService = userService;
        this.roleService = roleService;
        this.templateService = templateService;
    }


    @Override
    public String getUserManagement(Model model) {
        List<User> users = userService.findAll();
        List<Role> allRoles = roleService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("allRoles", allRoles);

        return templateService.getUserManagementView(model);
    }

    @Override
    public String assignRoles(Long userId, List<Long> selectedRoles, RedirectAttributes redirectAttributes) {
        User user = userService.getUserById(userId);
        List<Role> roles = roleService.getRolesById(selectedRoles);
        user.setRoles(new ArrayList<>(roles));

        try {
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Roles updated successfully!");
            return templateService.getAssignRolesSuccessfulView(userId, selectedRoles, redirectAttributes);
        }
        catch (UserCouldNotBeSavedInTheDatabaseException e) {
            logger.warn("Error adding task. Please try again.");
            redirectAttributes.addFlashAttribute("errorMessage", "Could not update the roles. Please try again.");
        }
        return templateService.getAssignRolesUnsuccessfulView(userId, selectedRoles, redirectAttributes);
    }

    @Override
    public String getAdminPanel() {
        return templateService.getAdminPanel();
    }
}
