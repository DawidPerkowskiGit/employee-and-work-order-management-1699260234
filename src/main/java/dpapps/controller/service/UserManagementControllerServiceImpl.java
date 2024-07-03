package dpapps.controller.service;

import dpapps.controller.service.templateservice.ErrorTemplateService;
import dpapps.controller.service.templateservice.UserManagementTemplateService;
import dpapps.exception.UserCouldNotBeSavedInTheDatabaseException;
import dpapps.exception.UserNotFoundException;
import dpapps.model.User;
import dpapps.model.repository.service.UserService;
import dpapps.model.repository.service.VerificationService;
import dpapps.security.changepassword.ChangePasswordDto;
import dpapps.security.userregistration.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Component
public class UserManagementControllerServiceImpl implements UserManagementControllerService {

    private final UserService userService;
    private final VerificationService verificationService;
    private final UserManagementTemplateService templateService;

    private final ErrorTemplateService errorTemplateService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public UserManagementControllerServiceImpl(UserService userService, VerificationService verificationService,
                                               UserManagementTemplateService templateService, ErrorTemplateService errorTemplateService) {
        this.userService = userService;
        this.verificationService = verificationService;
        this.templateService = templateService;
        this.errorTemplateService = errorTemplateService;
    }

    public String register(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return templateService.getRegisterView(model);
    }

    public String processRegister(UserDto userDto, BindingResult result, Model model) {
        User existingUser;

        if (!userService.existsByLogin(userDto.getLogin())) {
            userService.add(userDto);
            setupVerification(userDto.getLogin());
            return templateService.getAfterRegistrationView();
        }

        try {
            existingUser = userService.findByLogin(userDto.getLogin());
        }
        catch (UserNotFoundException e) {
            logger.warn(e.getMessage());
            return errorTemplateService.getNotFoundView();
        }

        if (existingUser != null && existingUser.getLogin() != null && !existingUser.getLogin().isEmpty()) {
            result.rejectValue("login", null, "There is already an account registered with the same login");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return templateService.getRegisterView(model);
        }
        return templateService.getAfterRegistrationView();
    }

    private void setupVerification(String login) {

        try {
            User user = userService.findByLogin(login);
            String verificationCode = verificationService.generateVerificationCode();
            verificationService.assignVerificationCodeToUser(user, verificationCode);
            verificationService.sendVerificationEmail(user, verificationCode);
        }
        catch (Exception e) {
            logger.warn(e.getMessage());
        }

    }

    public String processLogin() {
        return templateService.getLoginView();
    }

    @Override
    public String changePassword(Model model) {
        ChangePasswordDto userPassword = new ChangePasswordDto();
        model.addAttribute("userpass", userPassword);
        return templateService.getChangePasswordView(model);
    }

    @Override
    public String processChangePassword(ChangePasswordDto dto, BindingResult bindingResult, Model model) {
        User existingUser;
        try {
            existingUser = userService.findByLoginAndEmail(dto.getLogin(), dto.getEmail());
        } catch (UserNotFoundException e) {
            logger.warn(e.getMessage());
            return errorTemplateService.getNotFoundView();
        }


        if (existingUser != null && existingUser.getLogin() != null && !existingUser.getLogin().isEmpty() && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            if (dto.getPassword().equals(dto.getRepeatedPassword())) {
                userService.changePassword(dto.getLogin(), dto.getPassword());
            }
            else {
                bindingResult.rejectValue("password", null,"Passwords do not match");
            }
        }
        else {
            bindingResult.rejectValue("login", null, "Invalid login or email");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("userpass", dto);
            return templateService.getChangePasswordView(model);
        }
        return templateService.getIndexView();
    }

    @Override
    public String processUserVerification(String code, Model model) {
       boolean result = verificationService.processVerification(code);
       if (result) {
           model.addAttribute("successMessage", "Account verification was successful!");
           return templateService.getVerificationResultView();
       }
       model.addAttribute("errorMessage", "Your account could not be verified");
       return templateService.getVerificationResultView();
    }

    @Override
    public String getProfile(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        }
        catch (Exception e) {
            logger.warn(e.getMessage());
            return errorTemplateService.getNotFoundView();
        }

        model.addAttribute("user", user);

        return templateService.getUserProfileView(model);
    }

    @Override
    public String updateProfile(User updatedUser, Model model) {
        User user = userService.getUserById(updatedUser.getId());
        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());

        try {
            userService.saveUser(user);
            model.addAttribute("successMessage", "Your profile was updated successfully!");
        }
        catch (UserCouldNotBeSavedInTheDatabaseException e) {
            logger.info("Could not update your profile");
            model.addAttribute("errorMessage", "Could not update your profile");
        }
        
        return templateService.getUserProfileView(model);
    }

    @Override
    public String getMyAccount() {
        return templateService.getMyAccount();
    }


}
