package dpapps.model.repository.service;

import dpapps.model.TaskReview;
import dpapps.model.repository.TaskReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskReviewServiceImpl implements TaskReviewService {

    private final TaskReviewRepository taskReviewRepository;

    public TaskReviewServiceImpl(TaskReviewRepository taskReviewRepository) {
        this.taskReviewRepository = taskReviewRepository;
    }

    @Override
    public void save(TaskReview taskReview) {
        taskReviewRepository.save(taskReview);
    }
}
