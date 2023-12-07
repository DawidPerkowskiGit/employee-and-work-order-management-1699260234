package dpapps.model.repository.service;

import dpapps.exception.WorkingLogLatestEntryNotFoundException;
import dpapps.model.User;
import dpapps.model.WorkingLog;
import dpapps.model.repository.WorkingLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WorkingLogServiceImpl implements WorkingLogService {

    private final WorkingLogRepository workingLogRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public WorkingLogServiceImpl(WorkingLogRepository workingLogRepository) {
        this.workingLogRepository = workingLogRepository;
    }

    @Override
    public WorkingLog findLatestEntry(User user) {
        try {
            return workingLogRepository.findLatest(user).orElseThrow(() -> new WorkingLogLatestEntryNotFoundException("Latest working log could not be found"));
        } catch (WorkingLogLatestEntryNotFoundException e) {
            logger.warn(e.getMessage());
        }
        return new WorkingLog();
    }
}
