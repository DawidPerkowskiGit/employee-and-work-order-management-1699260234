package dpapps.controller.service.templateservice;

import org.springframework.ui.Model;

public interface UserManagementTemplateService {
    /**
     * Returns registration view
     */
    String getRegisterView(Model model);

    /**
     * Returns successful registration view
     */
    String getAfterRegistrationView();

    /**
     * Returns after logging-in view
     */
    String getLoginView();

    /**
     * Returns change password view
     */
    String getChangePasswordView(Model model);

    /**
     * Returns index view
     */
    String getIndexView();

    /**
     * Returns verification result view
     */
    String getVerificationResultView();

    /**
     * Returns User profile view
     */
    String getUserProfileView(Model model);
}
