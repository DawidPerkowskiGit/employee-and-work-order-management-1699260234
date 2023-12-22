package dpapps.model;

import java.time.LocalDate;

public class WorkingLogsForm {

    private LocalDate startDate;
    private LocalDate endDate;

    // getters and setters

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
