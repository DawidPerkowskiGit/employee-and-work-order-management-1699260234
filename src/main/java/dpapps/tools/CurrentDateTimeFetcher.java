package dpapps.tools;

import dpapps.constants.DateConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;


public class CurrentDateTimeFetcher {

    private HashMap<String, String> localeMap = new HashMap<>();

    public CurrentDateTimeFetcher() {
        localeMap.put("EU", "Europe/London");
    }

    public LocalTime localTime() {
        return parametrizedTime(this.localeMap.get(DateConstants.DATE_LOCALE));
    }

    private LocalTime parametrizedTime(String zoneId) {
        return LocalTime.now(ZoneId.of(zoneId));
    }

    public LocalDate localDate() {
        return parametrizedDate(this.localeMap.get(DateConstants.DATE_LOCALE));
    }

    private LocalDate parametrizedDate(String zoneId) {
        return LocalDate.now(ZoneId.of(zoneId));
    }
}
