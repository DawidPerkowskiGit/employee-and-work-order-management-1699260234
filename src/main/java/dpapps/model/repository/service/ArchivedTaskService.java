package dpapps.model.repository.service;

import dpapps.model.Task;

public interface ArchivedTaskService {

    /**
     * Archives completed task
     */
    void archiveTask(Task task);

}
