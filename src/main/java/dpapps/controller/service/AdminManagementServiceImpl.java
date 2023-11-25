package dpapps.controller.service;

import dpapps.model.Role;
import dpapps.model.User;
import dpapps.model.repository.service.RoleService;
import dpapps.model.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminManagementServiceImpl implements AdminManagementService {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminManagementServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        List<Role> allRoles = roleService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("allRoles", allRoles);

        return "/admin/user-management";
    }

    @Override
    public String assignRoles(Long userId, List<Long> selectedRoles) {
        User user = userService.getUserById(userId);

        List<Role> roles = roleService.getRolesById(selectedRoles);

        user.setRoles(new ArrayList<>(roles));

        userService.saveUser(user);

        return "redirect:/admin/user-management";
    }
}
