package dpapps.controller.service;

import dpapps.controller.service.templateservice.ErrorTemplateService;
import dpapps.controller.service.templateservice.WorkTimeTemplateService;
import dpapps.model.User;
import dpapps.model.WorkingLog;
import dpapps.model.repository.service.UserService;
import dpapps.model.repository.service.WorkingLogService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class WorkTimeManagementServiceImpl implements WorkTimeManagementService{

    private final WorkTimeTemplateService workTimeTemplateService;

    private final WorkingLogService workingLogService;

    private final UserService userService;

    private final ErrorTemplateService errorTemplateService;

    public WorkTimeManagementServiceImpl(WorkTimeTemplateService workTimeTemplateService, WorkingLogService workingLogService, UserService userService, ErrorTemplateService errorTemplateService) {
        this.workTimeTemplateService = workTimeTemplateService;
        this.workingLogService = workingLogService;
        this.userService = userService;
        this.errorTemplateService = errorTemplateService;
    }

    @Override
    public String getPanel(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        }
        catch (Exception e) {
            return errorTemplateService.getNotFoundView();
        }
        WorkingLog latestEntry = workingLogService.findLatestEntry(user);
        model.addAttribute("latestEntry", latestEntry);
        return workTimeTemplateService.getPanel(model);
    }

}
