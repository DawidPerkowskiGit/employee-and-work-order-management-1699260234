package dpapps.model.repository.service;

import dpapps.exception.TaskNotificationNotFoundException;
import dpapps.model.Task;
import dpapps.model.TaskNotification;
import dpapps.model.User;

import java.util.List;

public interface TaskNotificationService {
    /**
     * Persists task notification
     */
    boolean save(TaskNotification taskNotification);

    /**
     * Fetches notifications which need to be communicated to the user
     */

    List<TaskNotification> getNotifications(Long user_id);


    /**
     * Composes task notification based on Task
     */
    TaskNotification prepareNotification(Task task);

    /**
     * Get task notification by id
     */

    TaskNotification getNotificationById(Long id) throws TaskNotificationNotFoundException;

    /**
     * Get task notification by task
     */
    TaskNotification getNotificationByTask(Task task) throws TaskNotificationNotFoundException;

    /**
     * Deletes notifications related to a user
     */

    void deleteNotifications(Long userId);

    /**
     * Deletes single notification based on id
     */

    void delete(TaskNotification notification);
}
