package dpapps.model.repository.service;

import dpapps.model.CodingLanguage;
import dpapps.model.Project;
import dpapps.model.Task;
import dpapps.model.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Autowired
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
        return this.taskRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return this.taskRepository.existsByName(name);
    }

    @Override
    public Task findByTaskId(Long taskId) {
        return this.taskRepository.findByTaskId(taskId);
    }

    @Override
    public boolean existsByTaskId(Long id) {
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
            System.out.println("Could not add new Task to a database");
            e.printStackTrace();
        }
        return false;
    }
}
