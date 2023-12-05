package dpapps.model.repository.service;

import dpapps.exception.TaskNotificationNotFoundException;
import dpapps.model.Task;
import dpapps.model.TaskNotification;
import dpapps.model.repository.TaskNotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskNotificationServiceImpl implements TaskNotificationService {

    private final TaskNotificationRepository taskNotificationRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TaskNotificationServiceImpl(TaskNotificationRepository taskNotificationRepository) {
        this.taskNotificationRepository = taskNotificationRepository;
    }

    @Override
    public boolean save(TaskNotification taskNotification) {
        try {
            this.taskNotificationRepository.save(taskNotification);
            return true;
        } catch (Exception e) {
            logger.warn("Could not save Task Notification in the database");
        }
        return false;
    }

    @Override
    public List<TaskNotification> getNotifications(Long user_id) {
        return this.taskNotificationRepository.findAllByUserId(user_id);
    }

    @Override
    public TaskNotification prepareNotification(Task task) {
        TaskNotification taskNotification = new TaskNotification();
        taskNotification.setTask(task);
        return taskNotification;
    }

    @Override
    public TaskNotification getNotificationById(Long id) throws TaskNotificationNotFoundException {

        return taskNotificationRepository.findById(id).orElseThrow(() -> new TaskNotificationNotFoundException("Could not find task notification with id '" + id + "'"));
    }

    @Override
    public TaskNotification getNotificationByTask(Task task) throws TaskNotificationNotFoundException {
        return taskNotificationRepository.findByTask(task).orElseThrow(() -> new TaskNotificationNotFoundException("Could not find task notification related to a task"));
    }

    @Override
    public void deleteNotifications(Long userId) {
        List<TaskNotification> taskNotifications = taskNotificationRepository.findAllByUserId(userId);

        for (TaskNotification notification : taskNotifications
        ) {
            delete(notification);
        }
    }

    @Override
    public void delete(TaskNotification notification) {
        taskNotificationRepository.delete(notification);
    }

}
