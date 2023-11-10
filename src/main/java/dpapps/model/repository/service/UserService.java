package dpapps.model.repository.service;

import dpapps.model.User;
import dpapps.security.userregistration.UserDto;

import java.util.List;

public interface UserService{

    public List<User> list();

    public String add(User user);

    public String add(UserDto user);

    public String delete(String login);

    public boolean existsByLogin(String login);

    User findByLogin(String login);
}
