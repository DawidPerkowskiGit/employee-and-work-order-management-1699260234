package dpapps.controller;

import dpapps.controller.service.UserManagementControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserManagementControllerService userManagementControllerService;

    @Autowired
    public UserController(UserManagementControllerService userManagementControllerService) {
        this.userManagementControllerService = userManagementControllerService;
    }
}
