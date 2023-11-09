package dpapps.model.repository.service;

import dpapps.model.User;

import java.util.List;

public interface UserService {

    public List<User> list();

    public String add(User user);

    public String delete(String login);

    public boolean existsByLogin(String login);
}
