package dpapps.model.repository.service;

import dpapps.model.BreakLog;
import dpapps.model.repository.BreakLogRepository;

public class BreakLogServiceImpl implements BreakLogService{

    private final BreakLogRepository breakLogRepository;

    public BreakLogServiceImpl(BreakLogRepository breakLogRepository) {
        this.breakLogRepository = breakLogRepository;
    }

    @Override
    public void save(BreakLog breakLog) {
        breakLogRepository.save(breakLog);
    }
}
