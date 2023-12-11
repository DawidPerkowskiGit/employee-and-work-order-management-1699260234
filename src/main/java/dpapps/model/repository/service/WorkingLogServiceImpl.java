package dpapps.model.repository.service;

import dpapps.exception.WorkingLogLatestEntryNotFoundException;
import dpapps.model.User;
import dpapps.model.WorkingLog;
import dpapps.model.repository.UserRepository;
import dpapps.model.repository.WorkingLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WorkingLogServiceImpl implements WorkingLogService {

    private final WorkingLogRepository workingLogRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    public WorkingLogServiceImpl(WorkingLogRepository workingLogRepository,
                                 UserRepository userRepository) {
        this.workingLogRepository = workingLogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WorkingLog findLatestEntry(User user) {

        Page<WorkingLog> page = workingLogRepository.findLatest(user, PageRequest.of(0, 1));
        try {
            return page.toList().get(0);
        }
        catch (IndexOutOfBoundsException e) {
            logger.warn("Working time list for this user is empty");
        }
        return new WorkingLog();
    }

    @Override
    public void save(WorkingLog workingLog) {
        workingLogRepository.save(workingLog);
    }

    @Override
    public void removeAllByUser(User user) {
        workingLogRepository.deleteWorkingLogByUser(user);
    }
}
