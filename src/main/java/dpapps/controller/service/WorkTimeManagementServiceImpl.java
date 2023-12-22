package dpapps.controller.service;

import dpapps.constants.AppConstants;
import dpapps.constants.DateConstants;
import dpapps.controller.service.templateservice.ErrorTemplateService;
import dpapps.controller.service.templateservice.WorkTimeTemplateService;
import dpapps.model.*;
import dpapps.model.repository.BreakLogRepository;
import dpapps.model.repository.WorkingLogRepository;
import dpapps.model.repository.service.BreakLogService;
import dpapps.model.repository.service.UserService;
import dpapps.model.repository.service.WorkingLogService;
import dpapps.tools.ListSeparateDateTimeCalculator;
import dpapps.tools.ReadableTimeFormat;
import dpapps.tools.SeparateDateTimeCalculator;
import dpapps.tools.StringToLocalDateConverter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkTimeManagementServiceImpl implements WorkTimeManagementService {

    private final WorkTimeTemplateService workTimeTemplateService;

    private final WorkingLogService workingLogService;

    private final UserService userService;

    private final ErrorTemplateService errorTemplateService;

    private final BreakLogService breakLogService;
    private final BreakLogRepository breakLogRepository;
    private final WorkingLogRepository workingLogRepository;

    public WorkTimeManagementServiceImpl(WorkTimeTemplateService workTimeTemplateService, WorkingLogService workingLogService, UserService userService, ErrorTemplateService errorTemplateService, BreakLogService breakLogService,
                                         BreakLogRepository breakLogRepository,
                                         WorkingLogRepository workingLogRepository) {
        this.workTimeTemplateService = workTimeTemplateService;
        this.workingLogService = workingLogService;
        this.userService = userService;
        this.errorTemplateService = errorTemplateService;
        this.breakLogService = breakLogService;
        this.breakLogRepository = breakLogRepository;
        this.workingLogRepository = workingLogRepository;
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
        BreakLog breakLog = breakLogService.findLatestBreak(user);
        model.addAttribute("latestBreak", breakLog);
        int breaksTaken = breakLogService.countBreaks(user);
        model.addAttribute("breaksTaken", breaksTaken);

        List<WorkingLog> allWorkingLogs = workingLogService.findAllByUser(user);
        ListSeparateDateTimeCalculator calc = new ListSeparateDateTimeCalculator();
        long timeWorked = calc.calculateWorktime(allWorkingLogs);

        List<BreakLog> allBreaks = breakLogService.findAllByUser(user);
        long timeOnBreak = calc.calculateBreaks(allBreaks);

        long timeWorkedWithoutBreaks = timeWorked - timeOnBreak;

        ReadableTimeFormat readableTimeFormat = new ReadableTimeFormat(timeWorkedWithoutBreaks);
        model.addAttribute("timeWorked", readableTimeFormat);


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

    @Override
    public String startBreak(RedirectAttributes redirectAttributes) {
        BreakLog breakLog = new BreakLog();

        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (Exception e) {
            return errorTemplateService.getNotFoundView();
        }

        WorkingLog workingLog = workingLogService.findLatestEntry(user);

        if (workingLog.isClockedIn() == false || breakLog.isOnBreak()) {
            redirectAttributes.addFlashAttribute("cantStartBreak", "You cant start your break since you are not working currently");
            return workTimeTemplateService.getStartBreak(redirectAttributes);
        }

        breakLog.setUser(user);

        breakLog.setStartTime(getCurrentTime());
        breakLog.setStartDate(getCurrentDate());
        breakLog.setOnBreak(true);

        breakLogRepository.save(breakLog);

        redirectAttributes.addFlashAttribute("breakStart", "You started your break!");

        return workTimeTemplateService.getStartBreak(redirectAttributes);
    }

    @Override
    public String stopBreak(RedirectAttributes redirectAttributes) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (Exception e) {
            return errorTemplateService.getNotFoundView();
        }

        WorkingLog workingLog = workingLogService.findLatestEntry(user);
        BreakLog breakLog = breakLogService.findLatestBreak(user);

        if (workingLog.isClockedIn() == false || breakLog.isOnBreak() == false) {
            redirectAttributes.addFlashAttribute("cantStopBreak", "You cant stop your break since you are not working currently");
            return workTimeTemplateService.getStartBreak(redirectAttributes);
        }


        breakLog.setEndTime(getCurrentTime());
        breakLog.setEndDate(getCurrentDate());
        breakLog.setOnBreak(false);
        breakLogService.save(breakLog);

        redirectAttributes.addFlashAttribute("breakStop", "You stopped your break");

        return workTimeTemplateService.getStopBreak(redirectAttributes);
    }

    @Override
    public String getLogs(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (Exception e) {
            return errorTemplateService.getNotFoundView();
        }
        List<WorkingLog> workingLogs = workingLogRepository.findWorkingLogByUser(user);

        List<ProcessedWorkingLog> processedLogs = new ArrayList<>();


        for (WorkingLog log : workingLogs
        ) {
            ProcessedWorkingLog processedWorkingLog = new ProcessedWorkingLog();
            processedWorkingLog.setLog(log);
            SeparateDateTimeCalculator calculator = new SeparateDateTimeCalculator(log);

            long timeWorked = calculator.timeSpanSeconds();
            ReadableTimeFormat readableTimeFormat = new ReadableTimeFormat(timeWorked);
            processedWorkingLog.setTime(readableTimeFormat);

            if (readableTimeFormat.getHours() < AppConstants.DAILY_WORKED_HOURS) {
                WorkingTimeIssue issue = new WorkingTimeIssue();
                issue.setReason("Anomalous working time entry.");
                processedWorkingLog.setIssue(issue);
            }

            processedLogs.add(processedWorkingLog);
        }
        WorkingLogsForm workingLogsForm = new WorkingLogsForm();
        model.addAttribute("workingLogsForm", workingLogsForm);
        model.addAttribute("workingLogs", processedLogs);
        return workTimeTemplateService.getWorkingLogs(model);
    }

    @Override
    public String getDateFilteredLogs(String startDate, String endDate, Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (Exception e) {
            return errorTemplateService.getNotFoundView();
        }

        WorkingLogsForm workingLogsForm = new WorkingLogsForm();
        StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter(DateConstants.DATE_FORMAT);
        LocalDate startDateFormat = stringToLocalDateConverter.convert(startDate);
        workingLogsForm.setStartDate(startDateFormat);
        LocalDate endDateFormat = stringToLocalDateConverter.convert(endDate);
        workingLogsForm.setEndDate(endDateFormat);


        List<WorkingLog> workingLogs = workingLogRepository.findWorkingLogByUserAndStartDateBetween(user, workingLogsForm.getStartDate(), workingLogsForm.getEndDate());

        List<ProcessedWorkingLog> processedLogs = new ArrayList<>();


        for (WorkingLog log : workingLogs
        ) {
            ProcessedWorkingLog processedWorkingLog = new ProcessedWorkingLog();
            processedWorkingLog.setLog(log);
            SeparateDateTimeCalculator calculator = new SeparateDateTimeCalculator(log);

            long timeWorked = calculator.timeSpanSeconds();
            ReadableTimeFormat readableTimeFormat = new ReadableTimeFormat(timeWorked);
            processedWorkingLog.setTime(readableTimeFormat);

            if (readableTimeFormat.getHours() < AppConstants.DAILY_WORKED_HOURS) {
                WorkingTimeIssue issue = new WorkingTimeIssue();
                issue.setReason("Anomalous working time entry.");
                processedWorkingLog.setIssue(issue);
            }

            processedLogs.add(processedWorkingLog);
        }

        model.addAttribute("workingLogs", processedLogs);
        return workTimeTemplateService.getWorkingLogs(model);
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
