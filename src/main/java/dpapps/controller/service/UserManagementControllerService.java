package dpapps.controller.service;

import dpapps.security.userregistration.UserDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface UserManagementControllerService {

    String register(Model model);

    String processRegister(UserDto userDto, BindingResult result, Model model);

    String processLogin();
}
