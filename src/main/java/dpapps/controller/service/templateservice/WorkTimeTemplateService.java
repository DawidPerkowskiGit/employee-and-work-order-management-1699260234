package dpapps.controller.service.templateservice;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface WorkTimeTemplateService {

    /**
     * Returns work time panel view
     */
    String getPanel(Model model);

    /**
     * Return work time panel after starting work
     */
    String getStartWork(RedirectAttributes redirectAttributes);
    /**
     * Return work time panel after stopping work
     */
    String getStopWork(RedirectAttributes redirectAttributes);
}
