package dpapps.model.repository.service;

import dpapps.model.CodingLanguage;
import dpapps.model.Project;
import dpapps.model.Task;
import dpapps.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TaskService {
    /**
     * Returns Task based on ID value
     */
    Task findById(Long id);

    /**
     * Find if Task exists based on id
     */

    boolean existsById(Long id);

    /**
     * Returns Task based on name
     */
    Task findByName(String name);

    /**
     * Find if Task exists based on name
     */

    boolean existsByName(String name);

    /**
     * Returns Task based on task_id value
     */
    Task findByTaskId(String taskId);

    /**
     * Find if Task exists based on task_id
     */

    boolean existsByTaskId(String id);

    /**
     * Returns all Tasks related to a Project
     */

    List<Task> findAllByProject(Project project);

    /**
     * Returns all Task related to a Coding Language
     */

    List<Task> findAllByCodingLanguage(CodingLanguage codingLanguage);

    /**
     * Persists the Task
     */
    boolean save(Task task);

    /**
     * Deletes task by Id
     */
    boolean delete(Long id);

    /**
     * Deletes all tasks by name
     */
    boolean deleteAllByName(String name);

    /**
     * Fetches all Tasks from persistence layer
     */
    List<Task> findAll();

    /**
     * Fetches this designer Tasks from persistence layer
     */
    List<Task> findAllByUser(User user);

    /**
     * Sets task status to completed
     */

    void setCompleted(Long id);

    /**
     * Gets all tasks based on filtering and sorting
     */
    Page<Task> getAllTasks(PageRequest pageRequest, String userFilter, String projectFilter, String languageFilter);
}
