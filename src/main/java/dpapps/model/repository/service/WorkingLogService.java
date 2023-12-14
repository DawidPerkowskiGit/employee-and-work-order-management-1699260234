package dpapps.model.repository.service;

import dpapps.model.User;
import dpapps.model.WorkingLog;

import java.util.List;

public interface WorkingLogService {

    /**
     * Fetches User's latest entry
     */
    public WorkingLog findLatestEntry(User user);

    /**
     * Saves new working log
     */
    void save(WorkingLog workingLog);

    /**
     * Remove all working logs for user
     */
    void removeAllByUser(User user);

    /**
     * Find user working logs
     */
    List<WorkingLog> findAllByUser(User user);
}
