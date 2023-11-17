package dpapps.model.repository.service;

import dpapps.model.Role;
import dpapps.model.User;
import dpapps.model.UserAndRolePair;
import dpapps.model.repository.RoleRepository;
import dpapps.model.repository.UserAndRolePairRepository;
import dpapps.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserAndRolePairServiceImpl implements UserAndRolePairService {

    private final UserAndRolePairRepository userAndRolePairRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserAndRolePairServiceImpl(UserAndRolePairRepository userAndRolePairRepository,
                                      RoleRepository roleRepository,
                                      UserRepository userRepository) {
        this.userAndRolePairRepository = userAndRolePairRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void grantUserRole(User user, Role role) {
        UserAndRolePair userAndRolePair = new UserAndRolePair();
        userAndRolePair.setUser(user);
        userAndRolePair.setRole(role);
        userAndRolePairRepository.save(userAndRolePair);
    }

    @Override
    public void grantUserRole(User user, String role) {

    }

    @Override
    public List<Role> getUserRoles(User user) {
        List<UserAndRolePair> userRoles = this.userAndRolePairRepository.findUserAndRolePairByUser(user);

        List<Role> roles = new LinkedList<>();

        for (UserAndRolePair userRole : userRoles
             ) {
            roles.add(userRole.getRole());
        }

        return roles;
    }

    @Override
    public List<String> getUserRoleNames(User user) {
        List<String> roleNames = new ArrayList<>();

        List<Role> roles = this.getUserRoles(user);

        for (Role role: roles
             ) {
            roleNames.add(role.getName());
        }

        return roleNames;
    }


}
