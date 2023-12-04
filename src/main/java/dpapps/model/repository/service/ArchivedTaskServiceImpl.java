package dpapps.model.repository.service;

import dpapps.model.ArchivedTask;
import dpapps.model.Task;
import dpapps.model.repository.ArchivedTaskRepository;
import dpapps.model.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class ArchivedTaskServiceImpl implements ArchivedTaskService{

    private final TaskRepository taskRepository;

    private final ArchivedTaskRepository archivedTaskRepository;

    public ArchivedTaskServiceImpl(TaskRepository taskRepository, ArchivedTaskRepository archivedTaskRepository) {
        this.taskRepository = taskRepository;
        this.archivedTaskRepository = archivedTaskRepository;
    }

    @Override
    public void archiveTask(Task task) {

        ArchivedTask archivedTask = new ArchivedTask(task);

        archivedTaskRepository.save(archivedTask);
        taskRepository.delete(task);

    }
}
