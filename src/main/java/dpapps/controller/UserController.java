package dpapps.controller;

import dpapps.controller.service.UserManagementControllerService;
import dpapps.model.User;
import dpapps.security.changepassword.ChangePasswordDto;
import dpapps.security.userregistration.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserManagementControllerService controllerService;


    public UserController(UserManagementControllerService controllerService) {
        this.controllerService = controllerService;
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return controllerService.register(model);
    }

    /**
     * Process user registration and save data in the database - POST
     */
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        return controllerService.processRegister(userDto, result, model);
    }

    /**
     * Display user login view - GET
     */
    @GetMapping("/login")
    public String login() {
        return controllerService.processLogin();
    }

    @GetMapping("/changepass")
    public String changePassword(Model model) {
        return controllerService.changePassword(model);
    }


    @PostMapping("/changepass/update")
    public String processChangePass(@Valid @ModelAttribute("userpass") ChangePasswordDto dto, BindingResult result, Model model) {
        return controllerService.processChangePassword(dto, result, model);
    }

    @GetMapping("/verify/{code}")
    public String verifyUser(@PathVariable String code, Model model) {
        return controllerService.processUserVerification(code, model);
    }

    @GetMapping("/user/profile")
    public String getProfile(Model model) {
        return controllerService.getProfile(model);
    }

    @PostMapping("/user/updateProfile")
    public String updateProfile(@ModelAttribute User updatedUser, Model model) {
        return this.controllerService.updateProfile(updatedUser, model);
    }

    @GetMapping("/user/account")
    public String getMyAccount() {
        return controllerService.getMyAccount();
    }


}
