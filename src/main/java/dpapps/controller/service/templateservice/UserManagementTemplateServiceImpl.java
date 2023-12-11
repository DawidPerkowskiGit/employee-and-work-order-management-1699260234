package dpapps.controller.service.templateservice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserManagementTemplateServiceImpl implements UserManagementTemplateService{

    @Override
    public String getRegisterView(Model model) {
        return "user/register";
    }

    @Override
    public String getAfterRegistrationView() {
        return "user/after-registration";
    }

    @Override
    public String getLoginView() {
        return "user/login";
    }

    @Override
    public String getChangePasswordView(Model model) {
        return "user/change-password";
    }

    @Override
    public String getIndexView() {
        return "index";
    }

    @Override
    public String getVerificationResultView() {
        return "user/verification-result";
    }

    @Override
    public String getUserProfileView(Model model) {
        return "user/profile";
    }

    @Override
    public String getMyAccount() {
        return "user/myaccount";
    }
}
