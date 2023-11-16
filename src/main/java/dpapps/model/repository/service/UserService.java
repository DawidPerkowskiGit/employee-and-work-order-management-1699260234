package dpapps.model.repository.service;

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
    User findByLogin(String login);

    /**
     * Returns user based on login and email
     */
    User findByLoginAndEmail(String login, String email);

    /**
     * Attempts to change user password
     */
    void changePassword(String login, String password);
}
