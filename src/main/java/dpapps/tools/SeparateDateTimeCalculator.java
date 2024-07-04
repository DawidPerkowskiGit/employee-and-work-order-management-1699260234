package dpapps.tools;

import dpapps.model.BreakLog;
import dpapps.model.WorkingLog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class SeparateDateTimeCalculator {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public SeparateDateTimeCalculator(WorkingLog workingLog) {

        boolean nullEndDate = false;
        boolean nullEndTime = false;

        int startYear = workingLog.getStartDate().getYear();
        int startMonth = workingLog.getStartDate().getMonthValue();
        int startDay = workingLog.getStartDate().getDayOfMonth();

        int startHour = workingLog.getStartTime().getHour();
        int startMinute = workingLog.getStartTime().getMinute();
        int startSecond = workingLog.getStartTime().getSecond();

        this.startDate = LocalDateTime.of(startYear, startMonth, startDay, startHour, startMinute, startSecond);

        if (workingLog.getEndTime() == null) {
            workingLog.setEndTime(LocalTime.now());
            nullEndTime = true;
        }
        if (workingLog.getEndDate() == null) {
            workingLog.setEndDate(LocalDate.now());
            nullEndDate = true;
        }

        int endYear = workingLog.getEndDate().getYear();
        int endMonth = workingLog.getEndDate().getMonthValue();
        int endDay = workingLog.getEndDate().getDayOfMonth();

        int endHour = workingLog.getEndTime().getHour();
        int endMinute = workingLog.getEndTime().getMinute();
        int endSecond = workingLog.getEndTime().getSecond();

        this.endDate = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute, endSecond);
        if (nullEndTime) {
            workingLog.setEndTime(null);
        }

        if (nullEndDate) {
            workingLog.setEndDate(null);
        }
    }

    public SeparateDateTimeCalculator(BreakLog breakLog) {
        int startYear = breakLog.getStartDate().getYear();
        int startMonth = breakLog.getStartDate().getMonthValue();
        int startDay = breakLog.getStartDate().getDayOfMonth();

        int startHour = breakLog.getStartTime().getHour();
        int startMinute = breakLog.getStartTime().getMinute();
        int startSecond = breakLog.getStartTime().getSecond();

        this.startDate = LocalDateTime.of(startYear, startMonth, startDay, startHour, startMinute, startSecond);

        if (breakLog.getEndTime() == null) {
            breakLog.setEndTime(LocalTime.now());
        }
        if (breakLog.getEndDate() == null) {
            breakLog.setEndDate(LocalDate.now());
        }

        int endYear = breakLog.getEndDate().getYear();
        int endMonth = breakLog.getEndDate().getMonthValue();
        int endDay = breakLog.getEndDate().getDayOfMonth();

        int endHour = breakLog.getEndTime().getHour();
        int endMinute = breakLog.getEndTime().getMinute();
        int endSecond = breakLog.getEndTime().getSecond();

        this.endDate = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute, endSecond);
    }

    public long timeSpanSeconds() {
        if (startDate.isAfter(endDate)) {
            return 0;
        }
        return startDate.until(endDate, ChronoUnit.SECONDS);
    }


}
