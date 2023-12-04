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

    String getTaskDetails(Model model);


    String getSuccessfulTaskCompletion(RedirectAttributes redirectAttributes, Long id);
}
