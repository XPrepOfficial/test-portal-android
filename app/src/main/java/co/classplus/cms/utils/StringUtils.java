package co.classplus.cms.utils;

import java.util.concurrent.TimeUnit;

public class StringUtils {

    public static String getTwoDigitString(String number) {
        int no = Integer.parseInt(number);
        if (no < 10) {
            return "0" + no;
        } else {
            return number;
        }
    }

    public static String getDurationFromMillis(long millis) {
        StringBuilder stringBuilder = new StringBuilder();
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        if (seconds >= 60) {
            if (minutes >= 60) {
                if (hours >= 24) {
                    stringBuilder.append(days).append(":");
                    hours = hours - TimeUnit.DAYS.toHours(days);
                    minutes = minutes - TimeUnit.DAYS.toMinutes(days);
                    seconds = seconds - TimeUnit.DAYS.toSeconds(days);
                }
                if (hours >= 1) {
                    stringBuilder.append(hours).append(":");
                    minutes = minutes - TimeUnit.HOURS.toMinutes(hours);
                    seconds = seconds - TimeUnit.HOURS.toSeconds(hours);
                }
                stringBuilder.append(minutes).append(":");
                seconds = seconds - TimeUnit.MINUTES.toSeconds(minutes);
                stringBuilder.append(seconds);
            } else {
                stringBuilder.append(minutes).append(":");
                seconds = seconds - TimeUnit.MINUTES.toSeconds(minutes);
                stringBuilder.append(seconds);
            }
        } else {
            stringBuilder.append("00:").append(seconds);
        }
        return stringBuilder.toString().trim();
    }

    public static String getDurationForReports(long millis) {
        StringBuilder stringBuilder = new StringBuilder();
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        if (seconds >= 60) {
            if (minutes >= 60) {
                if (hours >= 24) {
                    stringBuilder.append(days).append(" days ");
                    hours = hours - TimeUnit.DAYS.toHours(days);
                    minutes = minutes - TimeUnit.DAYS.toMinutes(days);
                    seconds = seconds - TimeUnit.DAYS.toSeconds(days);
                }
                if (hours >= 1) {
                    stringBuilder.append(hours).append("h ");
                    minutes = minutes - TimeUnit.HOURS.toMinutes(hours);
                    seconds = seconds - TimeUnit.HOURS.toSeconds(hours);
                }
                stringBuilder.append(minutes).append("m ");
                seconds = seconds - TimeUnit.MINUTES.toSeconds(minutes);
                stringBuilder.append(seconds).append("s ");
            } else {
                stringBuilder.append(minutes).append("m ");
                seconds = seconds - TimeUnit.MINUTES.toSeconds(minutes);
                stringBuilder.append(seconds);
            }
        } else {
            stringBuilder.append("0m ").append(seconds).append("s ");
        }
        return stringBuilder.toString().trim();
    }
}
