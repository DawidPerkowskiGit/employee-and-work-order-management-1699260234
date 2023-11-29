package dpapps.controller.service;

import dpapps.model.Task;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface OperatorControllerService {

    /**
     * Returns Operator panel view
     */
    String getPanel();

    /**
     * Returns add task view
     */
    String getAddTaskView(Model model);

    /**
     * Saves the task
     */
    String saveTask(Task task, RedirectAttributes redirectAttributes);
}
