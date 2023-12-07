package dpapps.controller.service.templateservice;

import org.springframework.ui.Model;

public interface WorkTimeTemplateService {

    /**
     * Returns work time panel view
     */
    String getPanel(Model model);
}
