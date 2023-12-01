package dpapps.model.repository.service;

import dpapps.model.Role;

import java.util.List;

public interface RoleService {

    /**
     * Determines if specific role exists in the database
     */
    boolean roleExists(String role);

    /**
     * Persists the new Role, returns result of saving the entity.
     */
    boolean addRole(String roleName);

    /**
     * Returns role name
     */
    String getName(Role role);

    /**
     * Returns Roles bases on its ID
     */
    List<Role> getRolesById(List<Long> listOfRoles);

    /**
     * Returns all Roles
     */
    List<Role> findAll();

    /**
     * Gets role by name
     */
    Role getRoleByName(String name);

    /**
     * Gets role by id
     */
    Role getRoleById(Long id);

}
