package dpapps.model.repository.service;

import dpapps.model.User;
import dpapps.security.userregistration.UserDto;

import java.util.List;

public interface UserService {

    List<User> list();

    String add(User user);

    String add(UserDto user);

    String delete(String login);

    boolean existsByLogin(String login);

    User findByLogin(String login);

    User findByLoginAndEmail(String login, String email);

    void changePassword(String login, String password);
}
