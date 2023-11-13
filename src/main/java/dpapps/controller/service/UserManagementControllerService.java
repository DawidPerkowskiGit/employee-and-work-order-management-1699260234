package dpapps.controller.service;

import dpapps.security.changepassword.ChangePasswordDTO;
import dpapps.security.userregistration.UserDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface UserManagementControllerService {

    String register(Model model);

    String processRegister(UserDto userDto, BindingResult result, Model model);

    String processLogin();

    String changePassword(Model model);

    String processChangePassword(ChangePasswordDTO dto, BindingResult result, Model model);
}
