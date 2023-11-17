package dpapps;

import dpapps.constants.RoleConstants;
import dpapps.model.User;
import dpapps.model.repository.UserAndRolePairRepository;
import dpapps.model.repository.UserRepository;
import dpapps.model.repository.service.RoleService;
import dpapps.model.repository.service.UserAndRolePairService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddRoles {

    private final RoleService roleService;

    private final UserRepository userRepository;

    private final UserAndRolePairService userAndRolePairService;

    @Autowired
    public AddRoles(RoleService roleService, UserRepository userRepository, UserAndRolePairService userAndRolePairService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.userAndRolePairService = userAndRolePairService;
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
    public void grantRole() {
        User user = userRepository.findByLogin("admin");
//        userAndRolePairService.grantUserRole();
    }
}
