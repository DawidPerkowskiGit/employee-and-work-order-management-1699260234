package dpapps.model.repository.service;

import dpapps.model.Role;
import dpapps.model.User;

import java.util.List;

public interface UserAndRolePairService {
    /**
     * Grants a user specified role
     */
    void grantUserRole(User user, Role role);

    /**
     * Grants a user specified role
     */
    void grantUserRole(User user, String role);

    /**
     * Returns all the user's roles
     */
    List<Role> getUserRoles(User user);

    /**
     * Return List of User's role names
     */
    List<String> getUserRoleNames(User user);
}
