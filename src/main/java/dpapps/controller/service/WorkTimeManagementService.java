package dpapps.controller.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface WorkTimeManagementService {

    /**
     * Returns Work manager panel
     */
    String getPanel(Model model);

    /**
     * Start work time logging
     */
    String startWork(RedirectAttributes redirectAttributes);

    /**
     * Stops work time logging
     */
    String stopWork(RedirectAttributes redirectAttributes);
}