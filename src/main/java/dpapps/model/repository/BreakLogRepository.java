package dpapps.model.repository;

import dpapps.model.BreakLog;
import dpapps.model.User;
import dpapps.model.WorkingLog;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakLogRepository extends JpaRepository<BreakLog, Long> {

    @Query("SELECT b FROM BreakLog b " +
            "WHERE b.user = :user " +
            "ORDER BY b.startDate DESC, b.startTime DESC")
    Page<BreakLog> findLatest(@Param("user") User user, Pageable pageable);

    @Transactional
    void deleteBreakLogByUser(User user);
}
