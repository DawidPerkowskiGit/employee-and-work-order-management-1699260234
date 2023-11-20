package dpapps;

import dpapps.constants.RoleConstants;
import dpapps.model.User;
import dpapps.model.repository.UserRepository;
import dpapps.model.repository.service.RoleService;
import dpapps.model.repository.service.UserService;
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

    @Test
    public void grantAdminRole() {
        User user = userRepository.findByLogin("admin");
        userService.grantUserRole(user, RoleConstants.ROLE_ADMIN);
    }

    @Test
    public void grantOperatorRole() {
        User user = userRepository.findByLogin("operator");
        userService.grantUserRole(user, RoleConstants.ROLE_OPERATOR);
    }

    @Test
    public void grantDesignerRole() {
        User user = userRepository.findByLogin("designer");
        userService.grantUserRole(user, RoleConstants.ROLE_DESIGNER);
    }
    @Test
    public void addTestingUser() {
        userService.add(user);
    }
}
