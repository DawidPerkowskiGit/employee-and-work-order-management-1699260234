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

    /**
     * Gets all tasks
     */
    String getTasksList(Model model);

    /**
     * Prepares sata to render Edit Task view
     */
    String getEditTask(Long taskId, RedirectAttributes redirectAttributes, Model model);

    /**
     * Saves edited task
     */
    String saveEditedTask(Task task, RedirectAttributes redirectAttributes);
}
