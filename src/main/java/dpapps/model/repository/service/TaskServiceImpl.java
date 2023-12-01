package dpapps.model.repository.service;

import dpapps.exception.TaskNotFoundException;
import dpapps.model.CodingLanguage;
import dpapps.model.Project;
import dpapps.model.Task;
import dpapps.model.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findById(Long id) {
        return this.taskRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return this.taskRepository.existsById(id);
    }

    @Override
    public Task findByName(String name) {
        try {
            return this.taskRepository.findByName(name).orElseThrow(() -> new TaskNotFoundException());
        }
        catch (TaskNotFoundException e) {
            logger.warn("Could not find Task with name '" + name + "' in the database.");
        }

        return new Task();
    }

    @Override
    public boolean existsByName(String name) {
        return this.taskRepository.existsByName(name);
    }

    @Override
    public Task findByTaskId(String taskId) {
        try {
            return this.taskRepository.findByTaskId(taskId).orElseThrow(() -> new TaskNotFoundException());
        }
        catch (TaskNotFoundException e) {
            logger.warn("Could not find Task with task id '" + taskId + "' in the database.");
        }

        return new Task();
    }

    @Override
    public boolean existsByTaskId(String id) {
        return this.taskRepository.existsByTaskId(id);
    }

    @Override
    public List<Task> findAllByProject(Project project) {
        return this.taskRepository.findAllByProject(project);
    }

    @Override
    public List<Task> findAllByCodingLanguage(CodingLanguage codingLanguage) {
        return this.taskRepository.findAllByCodingLanguage(codingLanguage);
    }

    @Override
    public boolean add(Task task) {
        try {
            this.taskRepository.save(task);
            return true;
        }
        catch (Exception e) {
            logger.warn("Could not save new Task in the database");
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.taskRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            logger.warn("Could not delete Task id = " + id + " from the database");
        }
        return false;
    }

    @Override
    public boolean deleteAllByName(String name) {
        try {
            this.taskRepository.deleteAllByName(name);
            return true;
        }
        catch (Exception e) {
            logger.warn("Could not delete Tasks with name '" + name + "' from the database");
        }
        return true;
    }
}
