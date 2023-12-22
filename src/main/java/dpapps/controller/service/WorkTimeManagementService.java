package dpapps.controller.service;

import dpapps.model.WorkingLogsForm;
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

    /**
     * Starts break logging
     */
    String startBreak(RedirectAttributes redirectAttributes);
    /**
     * Stops break logging
     */
    String stopBreak(RedirectAttributes redirectAttributes);

    /**
     * Get all user logs
     */
    String getLogs(Model model);

    /**
     * Gets filtered Working logs
     */
    String getDateFilteredLogs(String startDate, String endDate, Model model);
}