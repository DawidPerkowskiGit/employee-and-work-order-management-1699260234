package dpapps.model.repository.service;

import dpapps.model.ArchivedTask;
import dpapps.model.ArchivedTaskNotification;
import dpapps.model.Task;
import dpapps.model.TaskNotification;
import dpapps.model.repository.ArchivedTaskNotificationRepository;
import dpapps.model.repository.ArchivedTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivedTaskNotificationServiceImpl implements ArchivedTaskNotificationService{

    private final ArchivedTaskNotificationRepository repository;
    private final ArchivedTaskRepository archivedTaskRepository;

    public ArchivedTaskNotificationServiceImpl(ArchivedTaskNotificationRepository repository,
                                               ArchivedTaskRepository archivedTaskRepository) {
        this.repository = repository;
        this.archivedTaskRepository = archivedTaskRepository;
    }

    @Override
    public void create(ArchivedTask archivedTask) {
        ArchivedTaskNotification notification = new ArchivedTaskNotification();
        notification.setArchivedTask(archivedTask);
        repository.save(notification);
    }

    @Override
    public void remove(ArchivedTaskNotification archivedTaskNotification) {
        repository.delete(archivedTaskNotification);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }

    @Override
    public List<ArchivedTaskNotification> findAll() {
        return repository.findAll();
    }

    @Override
    public ArchivedTaskNotification findByTask(ArchivedTask task) {
        return repository.findArchivedTaskNotificationByArchivedTask(task);
    }

    @Override
    public boolean existsByTask(ArchivedTask task) {
        return repository.existsByArchivedTask(task);
    }

    @Override
    public void removeByTaskId(Long id) {

        ArchivedTask archivedTask = archivedTaskRepository.findArchivedTaskById(id).get();

        repository.deleteByArchivedTask(archivedTask);
    }
}
