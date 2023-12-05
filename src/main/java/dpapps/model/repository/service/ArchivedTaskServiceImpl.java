package dpapps.model.repository.service;

import dpapps.exception.ArchivedTaskNotFoundException;
import dpapps.model.ArchivedTask;
import dpapps.model.Task;
import dpapps.model.User;
import dpapps.model.repository.ArchivedTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivedTaskServiceImpl implements ArchivedTaskService {

    private final ArchivedTaskRepository archivedTaskRepository;

    private final ArchivedTaskNotificationService archivedTaskNotificationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ArchivedTaskServiceImpl(ArchivedTaskRepository archivedTaskRepository, ArchivedTaskNotificationService archivedTaskNotificationService) {
        this.archivedTaskRepository = archivedTaskRepository;
        this.archivedTaskNotificationService = archivedTaskNotificationService;
    }

    @Override
    public void archiveTask(Task task) {

        ArchivedTask archivedTask = new ArchivedTask(task);

        save(archivedTask);

        notifyOperators(archivedTask);
    }

    @Override
    public ArchivedTask findTaskById(Long id) {
        try {
            return archivedTaskRepository.findArchivedTaskById(id).orElseThrow(() -> new ArchivedTaskNotFoundException("Could not find Archived task with id '" + id + "' in the database."));
        } catch (ArchivedTaskNotFoundException e) {
            logger.warn(e.getMessage());
        }
        return new ArchivedTask();
    }

    public void notifyOperators(ArchivedTask archivedTask) {
        archivedTaskNotificationService.create(archivedTask);
    }

    @Override
    public List<ArchivedTask> findAll() {
        return archivedTaskRepository.findAll();
    }

    @Override
    public List<ArchivedTask> findAllByDesigner(User user) {
        return archivedTaskRepository.findArchivedTaskByUser(user);
    }

    @Override
    public void save(ArchivedTask task) {
        archivedTaskRepository.save(task);
    }
}
