package dpapps.model.repository.service;

import dpapps.model.ArchivedTask;
import dpapps.model.ArchivedTaskNotification;

import java.util.List;

public interface ArchivedTaskNotificationService {

    /**
     * Composes notification for Archived Task
     */

    void create(ArchivedTask archivedTask);

    /**
     * Remove a notification
     */

    void remove(ArchivedTaskNotification archivedTaskNotification);

    /**
     * Remove all notifications
     */

    void removeAll();

    /**
     * Fetches all notifications
     */

    List<ArchivedTaskNotification> findAll();

    /**
     * Fetches Notification by Task
     */
    ArchivedTaskNotification findByTask(ArchivedTask task);

    /**
     * Determines if Archived Task notification exists for a Task
     */

    boolean existsByTask(ArchivedTask task);

    /**
     * Removes notification by Archived task id
     */
    void removeByTaskId(Long id);

}
