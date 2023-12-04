package dpapps.controller.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface DesignerControllerService {

    /**
     * Displays designer panel view
     */
    String getPanel(Model model);

    /**
     * Get allocated tasks
     */
    String getTasks(Model model);

    /**
     * Displays task details
     */
    String getTaskDetails(Model model, Long id);

    /**
     * Completes the task
     */
    String completeTask(Long id, RedirectAttributes redirectAttributes);
}
