package delivery.management.system.util;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TimeCalculator {

    public LocalDateTime durationTime(String time) {
        Duration duration = parseDuration(time);
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.plus(duration);
    }

    private Duration parseDuration(String durationStr) {
        String[] parts = durationStr.split(" ");
        int hours = 0;
        int minutes = 0;

        if (parts.length % 2 == 0) {
            for (int i = 0; i < parts.length; i += 2) {
                if (parts[i + 1].equals("hours")) {
                    hours = Integer.parseInt(parts[i]);
                } else if (parts[i + 1].equals("mins")) { // FIXME
                    minutes = Integer.parseInt(parts[i]);
                }
            }
        } else {
            minutes = Integer.parseInt(parts[0]);
        }
        return Duration.ofHours(hours).plusMinutes(minutes);
    }
}
