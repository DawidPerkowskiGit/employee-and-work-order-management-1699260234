package dpapps.controller.service;

import dpapps.model.ArchivedTask;
import dpapps.model.Task;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface OperatorControllerService {

    /**
     * Returns Operator panel view
     */
    String getPanel(Model model);

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
     * Gets taks based on sorting and filtering requirements
     * @return
     */
    public String getTasksList(int page,
                               int size,
                               String sortField,
                               String sortOrder,
                               String userFilter,
                               String projectFilter,
                               String languageFilter,
                               Model model);

    /**
     * Prepares sata to render Edit Task view
     */
    String getEditTask(Long taskId, RedirectAttributes redirectAttributes, Model model);

    /**
     * Saves edited task
     */
    String saveEditedTask(Task task, RedirectAttributes redirectAttributes);

    /**
     * Displays view where operator can submit a task review
     */
    String getReview(Long id, Model model);

    /**
     * Returns view with archived tasks
     */
    String getArchived(Model model);

    /**
     * Saves review in the database
     */
    String saveReview(ArchivedTask archivedTask);
}
