package dpapps.controller.service;

import dpapps.controller.service.templateservice.DesignerTemplateService;
import dpapps.controller.service.templateservice.ErrorTemplateService;
import dpapps.model.TaskNotification;
import dpapps.model.User;
import dpapps.model.repository.service.TaskNotificationService;
import dpapps.model.repository.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class DesignerControllerServiceImpl implements DesignerControllerService{

    private final DesignerTemplateService templateService;

    private final TaskNotificationService taskNotificationService;

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ErrorTemplateService errorTemplateService;

    public DesignerControllerServiceImpl(DesignerTemplateService templateService, TaskNotificationService taskNotificationService, UserService userService, ErrorTemplateService errorTemplateService) {
        this.templateService = templateService;
        this.taskNotificationService = taskNotificationService;
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
            logger.warn("Could not find authenticated user");
            return errorTemplateService.getNotFoundView();
        }

        Long userId = user.getId();
        List<TaskNotification> notifications = taskNotificationService.getNotifications(userId);
        taskNotificationService.setNotificationsNotNeededToDisplay(userId);
        model.addAttribute("notifications", notifications);
        return templateService.getDesignerPanelView(model);
    }
}
