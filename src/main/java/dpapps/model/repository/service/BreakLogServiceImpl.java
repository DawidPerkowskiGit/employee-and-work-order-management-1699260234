package dpapps.model.repository.service;

import dpapps.model.BreakLog;
import dpapps.model.User;
import dpapps.model.repository.BreakLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BreakLogServiceImpl implements BreakLogService {

    private final BreakLogRepository breakLogRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BreakLogServiceImpl(BreakLogRepository breakLogRepository) {
        this.breakLogRepository = breakLogRepository;
    }

    @Override
    public void save(BreakLog breakLog) {
        breakLogRepository.save(breakLog);
    }

    @Override
    public BreakLog findLatestBreak(User user) {
        Page<BreakLog> page = breakLogRepository.findLatest(user, PageRequest.of(0, 1));
        try {
            return page.toList().get(0);
        } catch (IndexOutOfBoundsException e) {
            logger.warn("This users' break list is empty");
        }
        return new BreakLog();
    }
}
