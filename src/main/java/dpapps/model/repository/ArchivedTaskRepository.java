package dpapps.model.repository;

import dpapps.model.ArchivedTask;
import dpapps.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ArchivedTaskRepository extends JpaRepository<ArchivedTask, Long> {
    Optional<ArchivedTask> findByName(String testTaskName);
}
