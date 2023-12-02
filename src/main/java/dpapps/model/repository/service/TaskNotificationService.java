package dpapps.model.repository.service;

import dpapps.exception.TaskNotificationNotFoundException;
import dpapps.model.Task;
import dpapps.model.TaskNotification;

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
     * Changes notification status
     */
    void setNotificationStatus(TaskNotification taskNotification, boolean status);
    /**
     * Changes all user's notification statuses not to be displayed.
     */
    void setNotificationsNotNeededToDisplay(Long userId);

    /**
     * Changes all user's notification statuses to be displayed.
     */
    void setNotificationsNeededToDisplay(Long userId);

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
}
