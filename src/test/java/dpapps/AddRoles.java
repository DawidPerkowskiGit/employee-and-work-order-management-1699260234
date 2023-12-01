package dpapps;

import dpapps.constants.RoleConstants;
import dpapps.exception.InvalidRoleNameException;
import dpapps.model.User;
import dpapps.model.repository.UserRepository;
import dpapps.model.repository.service.RoleService;
import dpapps.model.repository.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddRoles {

    private final RoleService roleService;

    private final UserRepository userRepository;

    private final UserService userService;

    private final String[] userData = {"admin", "adminpass", "adminemail"};

    private final User user = new User(userData[0], userData[1], userData[2]);


    @Autowired
    public AddRoles(RoleService roleService, UserRepository userRepository, UserService userService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Disabled
    @Test
    public void addRoles() {
        if (!roleService.roleExists(RoleConstants.ROLE_ADMIN)) {
            this.roleService.addRole(RoleConstants.ROLE_ADMIN);
        }
        if (!roleService.roleExists(RoleConstants.ROLE_DESIGNER)) {
            this.roleService.addRole(RoleConstants.ROLE_DESIGNER);
        }
        if (!roleService.roleExists(RoleConstants.ROLE_OPERATOR)) {
            this.roleService.addRole(RoleConstants.ROLE_OPERATOR);
        }
    }

    @Disabled
    @Test
    public void grantAdminRole() throws InvalidRoleNameException {
        User user = userRepository.findByLogin("admin").get();
        userService.grantUserRole(user, RoleConstants.ROLE_ADMIN);
    }

    @Disabled
    @Test
    public void grantOperatorRole() throws InvalidRoleNameException {
        User user = userRepository.findByLogin("operator").get();
        userService.grantUserRole(user, RoleConstants.ROLE_OPERATOR);
    }

    @Disabled
    @Test
    public void grantDesignerRole() throws InvalidRoleNameException {
        User user = userRepository.findByLogin("designer").get();
        userService.grantUserRole(user, RoleConstants.ROLE_DESIGNER);
    }

    @Disabled
    @Test
    public void addTestingUser() {
        userService.add(user);
    }
}
