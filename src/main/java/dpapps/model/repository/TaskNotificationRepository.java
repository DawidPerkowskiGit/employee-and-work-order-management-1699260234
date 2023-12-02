package dpapps.model.repository;

import dpapps.model.Task;
import dpapps.model.TaskNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskNotificationRepository extends JpaRepository<TaskNotification, Long> {


    @Query("SELECT tn FROM TaskNotification tn WHERE tn.task.user.id =:user_id")
    List<TaskNotification> findAllByUserId(@Param("user_id") Long user_id);

    Optional<TaskNotification> findByTask(Task task);
}
