package dpapps.controller.service;

import dpapps.controller.service.templateservice.ErrorTemplateService;
import dpapps.controller.service.templateservice.WorkTimeTemplateService;
import dpapps.model.User;
import dpapps.model.WorkingLog;
import dpapps.model.repository.service.UserService;
import dpapps.model.repository.service.WorkingLogService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class WorkTimeManagementServiceImpl implements WorkTimeManagementService {

    private final WorkTimeTemplateService workTimeTemplateService;

    private final WorkingLogService workingLogService;

    private final UserService userService;

    private final ErrorTemplateService errorTemplateService;

    public WorkTimeManagementServiceImpl(WorkTimeTemplateService workTimeTemplateService, WorkingLogService workingLogService, UserService userService, ErrorTemplateService errorTemplateService) {
        this.workTimeTemplateService = workTimeTemplateService;
        this.workingLogService = workingLogService;
        this.userService = userService;
        this.errorTemplateService = errorTemplateService;
    }

    @Override
    public String getPanel(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (Exception e) {
            return errorTemplateService.getNotFoundView();
        }
        WorkingLog latestEntry = workingLogService.findLatestEntry(user);
        model.addAttribute("latestEntry", latestEntry);
        return workTimeTemplateService.getPanel(model);
    }

    @Override
    public String startWork(RedirectAttributes redirectAttributes) {
        WorkingLog workingLog = new WorkingLog();
        workingLog.setStartTime(getCurrentTime());
        workingLog.setStartDate(getCurrentDate());
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (Exception e) {
            return errorTemplateService.getNotFoundView();
        }
        workingLog.setUser(user);
        workingLog.setClockedIn(true);
        workingLogService.save(workingLog);
        redirectAttributes.addFlashAttribute("startSuccessful", "You successfully clocked in!");
        return workTimeTemplateService.getStartWork(redirectAttributes);
    }

    @Override
    public String stopWork(RedirectAttributes redirectAttributes) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (Exception e) {
            return errorTemplateService.getNotFoundView();
        }
        WorkingLog workingLog = workingLogService.findLatestEntry(user);
        workingLog.setEndTime(getCurrentTime());
        workingLog.setEndDate(getCurrentDate());
        workingLog.setClockedIn(false);
        workingLogService.save(workingLog);
        redirectAttributes.addFlashAttribute("stopSuccessful", "You successfully clocked out!");
        return workTimeTemplateService.getStopWork(redirectAttributes);
    }

    private LocalTime getCurrentTime() {
        LocalTime localTime = LocalTime.now();
        localTime = localTime.truncatedTo(ChronoUnit.SECONDS);
        System.out.println(localTime);
        return localTime;
    }

    private LocalDate getCurrentDate() {
        System.out.println(LocalDate.now());
        return LocalDate.now();
    }

}
