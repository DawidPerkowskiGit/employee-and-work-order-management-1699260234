package dpapps.model.repository.service;

import dpapps.model.ArchivedTask;
import dpapps.model.Task;
import dpapps.model.User;

import java.util.List;

public interface ArchivedTaskService {

    /**
     * Archives completed task
     */
    void archiveTask(Task task);

    /**
     * Returns archived task based on its id
     */

    ArchivedTask findTaskById(Long Id);

    /**
     * Notifies operators about completed Task, first operator to get notification prevents it from showing for others
     */

    void notifyOperators(ArchivedTask archivedTask);

    /**
     * Fetches all Archived tass from persistence layer
     */

    List<ArchivedTask> findAll();

    /**
     * Finds archived task by designer who completed it
     */

    List<ArchivedTask> findAllByDesigner(User user);

    /**
     * Saves task
     */

    void save(ArchivedTask task);
}
