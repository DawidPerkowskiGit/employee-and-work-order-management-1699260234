package dpapps.model.repository.service;

import dpapps.model.User;
import dpapps.model.WorkingLog;

public interface WorkingLogService {

    /**
     * Fetches User's latest entry
     */
    public WorkingLog findLatestEntry(User user);
}
