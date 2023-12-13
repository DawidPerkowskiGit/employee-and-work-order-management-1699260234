package dpapps.model.repository.service;

import dpapps.model.BreakLog;
import dpapps.model.User;

public interface BreakLogService {

    /**
     * Persists the break
     */
    void save(BreakLog breakLog);

    /**
     * Finds the latest users' break
     */
    BreakLog findLatestBreak(User user);
}
