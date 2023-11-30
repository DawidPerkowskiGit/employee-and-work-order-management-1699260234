package dpapps.model.repository;

import dpapps.model.CodingLanguage;
import dpapps.model.Project;
import dpapps.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByName(String name);

    boolean existsByName(String name);

    Optional<Task> findByTaskId(String taskId);

    boolean existsByTaskId(String id);

    List<Task> findAllByProject(Project project);

    List<Task> findAllByCodingLanguage(CodingLanguage codingLanguage);

    @Transactional
    void deleteAllByName(String name);
}


