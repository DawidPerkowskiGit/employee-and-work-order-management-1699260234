package dpapps.controller.service;

import dpapps.model.User;
import dpapps.model.repository.service.UserService;
import dpapps.security.userregistration.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Component
public class UserManagementControllerServiceImpl implements UserManagementControllerService {

    private final UserService userService;

    @Autowired
    public UserManagementControllerServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public String register(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    public String processRegister(UserDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findByLogin(userDto.getLogin());

        if (existingUser != null && existingUser.getLogin() != null && !existingUser.getLogin().isEmpty()) {
            result.rejectValue("login", null, "There is already an account registered with the same login");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }


        userService.add(userDto);
        return "redirect:/index";
    }

    public String processLogin() {
        return "login";
    }
}
