package dpapps.tools;

import dpapps.model.BreakLog;
import dpapps.model.SeparateDateTimeModel;
import dpapps.model.WorkingLog;

import java.util.List;

public class ListSeparateDateTimeCalculator {

    public ListSeparateDateTimeCalculator() {

    }

    public long calculateWorktime(List<WorkingLog> models) {
        if (models.isEmpty()) {
            return 0;
        }

        long totalTime = 0;

        for (WorkingLog model: models
             ) {
            SeparateDateTimeCalculator calculator = new SeparateDateTimeCalculator(model);
            totalTime+= calculator.timeSpanSeconds();
        }

        return totalTime;
    }

    public long calculateBreaks(List<BreakLog> models) {
        if (models.isEmpty()) {
            return 0;
        }

        long totalTime = 0;

        for (BreakLog model: models
        ) {
            SeparateDateTimeCalculator calculator = new SeparateDateTimeCalculator(model);
            totalTime+= calculator.timeSpanSeconds();
        }

        return totalTime;
    }
}
