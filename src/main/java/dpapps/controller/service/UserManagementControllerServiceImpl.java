package dpapps.controller.service;

import dpapps.model.User;
import dpapps.model.repository.UserRepository;
import dpapps.model.repository.service.UserService;
import dpapps.model.repository.service.VerificationService;
import dpapps.security.changepassword.ChangePasswordDto;
import dpapps.security.userregistration.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Component
public class UserManagementControllerServiceImpl implements UserManagementControllerService {

    private final UserService userService;

    private final VerificationService verificationService;
    private final UserRepository userRepository;

    @Autowired
    public UserManagementControllerServiceImpl(UserService userService, VerificationService verificationService,
                                               UserRepository userRepository) {
        this.userService = userService;
        this.verificationService = verificationService;
        this.userRepository = userRepository;
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

        this.setupVerification(userDto.getLogin());

        return "afterregistration";
    }

    private void setupVerification(String login) {
        User user = userRepository.findByLogin(login);
        String verificationCode = this.verificationService.generateVerificationCode();
        this.verificationService.assignVerificationCodeToUser(user, verificationCode);
        this.verificationService.sendVerificationEmail(user, verificationCode);
    }

    public String processLogin() {
        return "login";
    }

    @Override
    public String changePassword(Model model) {
        ChangePasswordDto userpass = new ChangePasswordDto();
        model.addAttribute("userpass", userpass);
        return "changepass";
    }

    @Override
    public String processChangePassword(ChangePasswordDto dto, BindingResult result, Model model) {
        User existingUser = userService.findByLoginAndEmail(dto.getLogin(), dto.getEmail());

        if (existingUser != null && existingUser.getLogin() != null && !existingUser.getLogin().isEmpty() && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            if (dto.getPassword().equals(dto.getRepeatedPassword())) {
                userService.changePassword(dto.getLogin(), dto.getPassword());
            }
            else {
                result.rejectValue("password", null,"Passwords do not match");
            }
        }
        else {
            result.rejectValue("login", null, "Invalid login or email");
        }

        if (result.hasErrors()) {
            model.addAttribute("userpass", dto);
            return "/changepass";
        }
        return "index";
    }

    @Override
    public String processUserVerification(String code) {
       boolean result = verificationService.processVerification(code);
       if (result) {
           return "verificationSuccessful";
       }
       return "verificationUnsuccessful";
    }

    @Override
    public String getProfile(Model model) {
        User user = userService.getAuthenticatedUser();
        model.addAttribute("user", user);

        return "profile";
    }

    @Override
    public String updateProfile(User updatedUser) {
        User user = userService.getUserById(updatedUser.getId());

        user.setEmail(updatedUser.getEmail());

        userService.saveUser(user);
        return "redirect:/profile";
    }


}
