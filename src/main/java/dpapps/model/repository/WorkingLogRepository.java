package dpapps.model.repository;

import dpapps.model.User;
import dpapps.model.WorkingLog;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkingLogRepository extends JpaRepository<WorkingLog, Long> {

    @Query("SELECT wl FROM WorkingLog wl " +
            "WHERE wl.user = :user " +
            "ORDER BY wl.startDate DESC, wl.startTime DESC")
    Page<WorkingLog> findLatest(@Param("user") User user, Pageable pageable);

    @Transactional
    void deleteWorkingLogByUser(User user);
}
