package com.ghostchu.botdefender.sysio.util;

import org.jetbrains.annotations.NotNull;

public class TimeUtil {

    public static long convert(@NotNull String time) {
        // Convert time string like "3m" "1h" "3d" "5mo" "1y" to milliseconds
        // time format: [0-9]+[smhdw]
        // time unit: s, m, h, d, w, y
        // time unit: second, minute, hour, day, week, year

        String[] timeUnit = {"s", "m", "h", "d", "w", "y"};
        int[] timeUnitValue = {1, 60, 3600, 86400, 604800, 31536000};

        int timeUnitIndex = 0;
        for (int i = 0; i < timeUnit.length; i++) {
            if (time.endsWith(timeUnit[i])) {
                timeUnitIndex = i;
                break;
            }
        }

        int timeValue = Integer.parseInt(time.substring(0, time.length() - timeUnit[timeUnitIndex].length()));
        return (long) timeValue * timeUnitValue[timeUnitIndex] * 1000;
    }

    @NotNull
    public static String convert(long time) {
        // Convert timestamp to human readable string
        // Example: 1d 3m 4mo 5y
        // time format: [0-9]+[smhdw]
        // time unit: s, m, h, d, w, y
        // time unit: second, minute, hour, day, week, year
        time /= 1000;
        String[] timeUnit = {"s", "m", "h", "d", "w", "y"};
        int[] timeUnitValue = {1, 60, 3600, 86400, 604800, 31536000};

        int timeUnitIndex = 0;
        for (int i = 0; i < timeUnit.length; i++) {
            if (time >= timeUnitValue[i]) {
                break;
            }
        }

        return (time / timeUnitValue[timeUnitIndex]) + timeUnit[timeUnitIndex];
    }

}
