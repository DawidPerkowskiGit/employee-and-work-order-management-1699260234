package dpapps.tools;

import lombok.Getter;

@Getter
public class ReadableTimeFormat {

    private long hours = 0;

    private long minutes = 0;

    private long seconds = 0;

    private String readableTime;

    public ReadableTimeFormat(long seconds) {
        this.seconds = seconds % 60;
        long tempTime = seconds / 60;
        this.minutes = tempTime % 60;
        this.hours = tempTime/60;
        this.readableTime = hours + "h " + minutes + "m " + this.seconds + "s";
    }


}
