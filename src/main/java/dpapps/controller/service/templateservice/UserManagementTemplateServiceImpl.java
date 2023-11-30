package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserManagementTemplateServiceImpl implements UserManagementTemplateService{

    @Override
    public String getRegisterView(Model model) {
        return "register";
    }

    @Override
    public String getAfterRegistrationView() {
        return "after-registration";
    }

    @Override
    public String getLoginView() {
        return "login";
    }

    @Override
    public String getChangePasswordView(Model model) {
        return "change-password";
    }

    @Override
    public String getIndexView() {
        return "index";
    }

    @Override
    public String getVerificationResultView() {
        return "verification-result";
    }

    @Override
    public String getUserProfileView(Model model) {
        return "profile";
    }
}
