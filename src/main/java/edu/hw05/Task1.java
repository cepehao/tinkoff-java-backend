package edu.hw05;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

final class Task1 {
    private static final Pattern DATE_PERIOD_PATTERN
        = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2})$");

    private Task1() {

    }

    @SuppressWarnings("MagicNumber")
    public static String avgTime(String[] stringDatePeriods) {
        var totalTime = Duration.ZERO;

        for (var stringDatePeriod: stringDatePeriods) {
            var matcher = DATE_PERIOD_PATTERN.matcher(stringDatePeriod);

            if (!matcher.matches()) {
                throw new IllegalArgumentException(
                    "Строка должна соответствовать формату yyyyy-mm-dd, hh:mm - yyyy-mm-dd, hh:mm"
                );
            }

            LocalDateTime startDateTime;
            LocalDateTime endDateTime;

            try {
                startDateTime = LocalDateTime.parse(matcher.group(1) + "T" + matcher.group(2));
                endDateTime = LocalDateTime.parse(matcher.group(3) + "T" + matcher.group(4));
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Введена некорректная дата");
            }

            totalTime = totalTime.plus(Duration.between(startDateTime, endDateTime));
        }

        var avgTime = Duration.ofMillis(totalTime.toMillis() / stringDatePeriods.length);

        var seconds = avgTime.getSeconds();
        var hours = seconds / 3600;
        var minutes = (seconds % 3600) / 60;

        return String.format("%dч %dм", hours, minutes);
    }
}
