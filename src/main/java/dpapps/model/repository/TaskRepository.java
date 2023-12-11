package dpapps.model.repository;

import dpapps.model.CodingLanguage;
import dpapps.model.Project;
import dpapps.model.Task;
import dpapps.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Task> findTasksByUser(User user);

    @Query("SELECT t FROM Task t " +
            "WHERE (:userFilter IS NULL OR t.user.name LIKE %:userFilter%) " +
            "AND (:projectFilter IS NULL OR t.project.name LIKE %:projectFilter%) " +
            "AND (:languageFilter IS NULL OR t.codingLanguage.name LIKE %:languageFilter%)")
    Page<Task> findAllTasks(
            @Param("userFilter") String userFilter,
            @Param("projectFilter") String projectFilter,
            @Param("languageFilter") String languageFilter,
            Pageable pageable);
}


