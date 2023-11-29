package dpapps.model.repository;

import dpapps.model.CodingLanguage;
import dpapps.model.Project;
import dpapps.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByName(String name);

    boolean existsByName(String name);

    Task findByTaskId(Long taskId);

    boolean existsByTaskId(Long id);

    List<Task> findAllByProject(Project project);

    List<Task> findAllByCodingLanguage(CodingLanguage codingLanguage);
}


