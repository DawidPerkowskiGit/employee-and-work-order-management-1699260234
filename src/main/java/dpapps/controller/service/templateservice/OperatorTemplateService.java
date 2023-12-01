package dpapps.controller.service.templateservice;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface OperatorTemplateService {

    /**
     * Returns operator panel view template
     */
    String getOperatorPanelView();

    /**
     * Returns add task view template
     */
    String getNewTaskView(Model model);

    /**
     * Redirects to successful task creation view
     */
    String getSuccessfulTaskCreationView(RedirectAttributes redirectAttributes);

    /**
     * Redirects to unsuccessful task creation view
     */
    String getUnsuccessfulTaskCreationView(RedirectAttributes redirectAttributes);

    /**
     * Returns view with all tasks
     */
    String getTasksList(Model model);

    /**
     * Returns view where a task can be edited
     */
    String getEditTaskView(Model model);

    /**
     * Returns a view where task was successfully edited
     */
    String getSuccessfulTaskEditView(RedirectAttributes redirectAttributes, Long id);

    /**
     * Returns a view where task was not successfully edited
     */
    String getUnsuccessfulTaskEditView(RedirectAttributes redirectAttributes, Long id);
}
