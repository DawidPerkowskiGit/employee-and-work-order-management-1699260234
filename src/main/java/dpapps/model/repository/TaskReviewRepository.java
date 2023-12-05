package dpapps.model.repository;

import dpapps.model.TaskReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskReviewRepository extends JpaRepository<TaskReview, Long> {
    Optional<TaskReview> findTaskReviewById(Long id);
}
