package dpapps.controller.service;

import dpapps.model.User;
import dpapps.security.changepassword.ChangePasswordDto;
import dpapps.security.userregistration.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface UserManagementControllerService {

    /**
     * Returns user registration view
     */
    String register(Model model);

    /**
     * Processes user registration and returns result view
     */
    String processRegister(UserDto userDto, BindingResult result, Model model);

    /**
     * Processes user logging in and returns result view
     */
    String processLogin();

    /**
     * Returns change password view
     */
    String changePassword(Model model);

    /**
     * Processes users change password request and renders result view
     */
    String processChangePassword(ChangePasswordDto dto, BindingResult result, Model model);

    /**
     * Attempts to verify the user, returns result view
     */
    String processUserVerification(String code);


    /**
     * Renders User profile view
     */
    String getProfile(Model model);

    /**
     * Updated user profile data
     */
    String updateProfile(User updatedUser);
}
