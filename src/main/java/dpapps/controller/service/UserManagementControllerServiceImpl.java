package dpapps.controller.service;

import dpapps.model.User;
import dpapps.model.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManagementControllerServiceImpl implements UserManagementControllerService {

    private final UserService userService;

    @Autowired
    public UserManagementControllerServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addUser(User user) {
        userService.add(user);
    }
}
