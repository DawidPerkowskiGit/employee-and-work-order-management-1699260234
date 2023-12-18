package dpapps.model;

import dpapps.tools.ReadableTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessedWorkingLog {

    private WorkingLog log;

    private WorkingTimeIssue issue;

    private ReadableTimeFormat time;
}
