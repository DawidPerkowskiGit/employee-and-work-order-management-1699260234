package dpapps.model.repository.service;

import dpapps.model.BreakLog;
import dpapps.model.User;

import java.util.List;

public interface BreakLogService {

    /**
     * Persists the break
     */
    void save(BreakLog breakLog);

    /**
     * Finds the latest users' break
     */
    BreakLog findLatestBreak(User user);

    /**
     * Counts user breaks
     */

    int countBreaks(User user);

    /**
     * Find user breaks
     */
    List<BreakLog> findAllByUser(User user);
}
