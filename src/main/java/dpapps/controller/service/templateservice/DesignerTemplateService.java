package dpapps.controller.service.templateservice;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface DesignerTemplateService {
    /**
     * Displays designer panel view
     */
    String getDesignerPanelView(Model model);


    /**
     * Returns tasks list view
     */
    String getTasksList(Model model);

    /**
     * Displays task details view
     */
    String getTaskDetails(Model model);

    /**
     * Returns task completed view
     */
    String getSuccessfulTaskCompletion();

    /**
     * Returns view with completed tasks
     */
    String getCompletedTasks(Model model);
}
