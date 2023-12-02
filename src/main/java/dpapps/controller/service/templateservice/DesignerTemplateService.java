package dpapps.controller.service.templateservice;

import org.springframework.ui.Model;

public interface DesignerTemplateService {
    /**
     * Displays designer panel view
     */
    String getDesignerPanelView(Model model);
}
