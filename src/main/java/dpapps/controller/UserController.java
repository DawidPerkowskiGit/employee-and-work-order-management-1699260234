package dpapps.controller;

import dpapps.controller.service.UserManagementControllerService;
import dpapps.security.changepassword.ChangePasswordDto;
import dpapps.security.userregistration.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserManagementControllerService service;

    @Autowired
    public UserController(UserManagementControllerService service) {
        this.service = service;
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return service.register(model);
    }

    /**
     * Process user registration and save data in the database - POST
     */
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        return service.processRegister(userDto, result, model);
    }

    /**
     * Display user login view - GET
     */
    @GetMapping("/login")
    public String login() {
        return service.processLogin();
    }

    @GetMapping("/changepass")
    public String changePassword(Model model) {
        return service.changePassword(model);
    }


    @PostMapping("/changepass/update")
    public String processChangePass(@Valid @ModelAttribute("userpass") ChangePasswordDto dto, BindingResult result, Model model) {
        return service.processChangePassword(dto, result, model);
    }

    @GetMapping("/verify/{code}")
    public String verifyUser(@PathVariable String code) {
        return service.processUserVerification(code);
    }


}
