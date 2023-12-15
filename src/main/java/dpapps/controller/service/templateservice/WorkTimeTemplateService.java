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

    /**
     * Return work time panel after starting a break
     */
    String getStartBreak(RedirectAttributes redirectAttributes);

    /**
     * Return work time panel after stopping a break
     */
    String getStopBreak(RedirectAttributes redirectAttributes);

    /**
     * Return all working logs view
     */
    String getWorkingLogs(Model model);
}
