package dpapps.model.repository.service;

import dpapps.model.BreakLog;

public interface BreakLogService {

    /**
     * Persists the break
     */
    void save(BreakLog breakLog);
}
