package dpapps.model.repository.service;

import dpapps.model.Role;
import dpapps.model.User;

public interface RoleService {

    /**
     * Determines if specific role exists in the database
     */
    boolean roleExists(String role);

    /**
     * Adds role to the database
     */
    void addRole(String roleName);

    /**
     * Returns role name
     */
    String getName(Role role);

}
