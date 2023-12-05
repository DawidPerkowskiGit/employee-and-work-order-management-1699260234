package dpapps.model.repository;

import dpapps.model.ArchivedTask;
import dpapps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchivedTaskRepository extends JpaRepository<ArchivedTask, Long> {
    Optional<ArchivedTask> findByName(String name);

    Optional<ArchivedTask> findArchivedTaskById(Long id);

    List<ArchivedTask> findArchivedTaskByUser(User user);



}


