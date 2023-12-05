package dpapps.model.repository;

import dpapps.model.ArchivedTask;
import dpapps.model.ArchivedTaskNotification;
import dpapps.model.Task;
import dpapps.model.TaskNotification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchivedTaskNotificationRepository extends JpaRepository<ArchivedTaskNotification, Long> {
    Optional<ArchivedTaskNotification> findArchivedTaskNotificationById(Long id);

    ArchivedTaskNotification findArchivedTaskNotificationByArchivedTask(ArchivedTask task);


    boolean existsByArchivedTask(ArchivedTask task);

    @Transactional
    void deleteByArchivedTask(ArchivedTask task);

}
