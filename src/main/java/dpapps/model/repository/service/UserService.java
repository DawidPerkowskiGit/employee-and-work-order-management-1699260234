package dpapps.model.repository.service;

import dpapps.exception.InvalidRoleNameException;
import dpapps.exception.UserCouldNotBeSavedInTheDatabaseException;
import dpapps.exception.UserNotFoundException;
import dpapps.model.Role;
import dpapps.model.User;
import dpapps.security.userregistration.UserDto;

import java.util.List;

public interface UserService {

    /**
     * Returns list of users
     */
    List<User> list();

    /**
     * Adds User Entity to the database
     */
    String add(User user);

    /**
     * Adds user to the database based on registration user model
     */
    String add(UserDto user);

    /**
     * Deletes user from the database.
     */
    String delete(String login);

    /**
     * Checks if user exists in the database
     */
    boolean existsByLogin(String login);

    /**
     * Returns user based on login
     */
    User findByLogin(String login) throws UserNotFoundException;

    /**
     * Returns user based on login and email
     */
    User findByLoginAndEmail(String login, String email) throws UserNotFoundException;

    /**
     * Attempts to change user password
     *
     * @return
     */
    boolean changePassword(String login, String password);


    /**
     * Adds Role to a User. Returns result of the modification. Role Entity is passed as an argument
     */
    boolean grantUserRole(User user, Role role) throws InvalidRoleNameException;

    /**
     * Adds Role to a User. Returns result of the modification. Role name is passed as an argument
     */
    boolean grantUserRole(User user, String roleName) throws InvalidRoleNameException;


    /**
     * Returns Users roles
     */
    List<Role> getUserRoles(User user);


    /**
     * Returns Users roles names
     */
    List<String> getUserRoleNames(User user);

    /**
     * Saves User to the database
     */
    void saveUser(User user) throws UserCouldNotBeSavedInTheDatabaseException;

    /**
     * Returns User based on its ID value
     */
    User getUserById(Long id);

    /**
     * Returns all users
     */
    List<User> findAll();

    /**
     * Returns currently authenticated user
     */
    User getAuthenticatedUser() throws Exception;

    /**
     * Returns Users based on their role name
     */
    List<User> findAllByRole(String roleName);

    /**
     * Returns Users based on their role id
     */
    List<User> findAllByRole(Long id);

}
