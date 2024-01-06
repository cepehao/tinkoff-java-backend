package edu.hw05;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

final class Task2 {
    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
    private static final int QUANTITY_MONTH_IN_YEAR = 12;
    private static final int THIRTEEN = 13;

    @SuppressWarnings("MagicNumber")
    public String getUnhappyFridays(int year) {
        if (year < 1000 || year > 3000) {
            throw new IllegalArgumentException("Рассматриваются только года с 1000 по 3000");
        }


        List<String> unhappyFridays = new ArrayList<>();

        for (int month = 1; month <= QUANTITY_MONTH_IN_YEAR; month++) {
            var curDate = LocalDate.of(year, month, THIRTEEN);

            if (curDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                unhappyFridays.add(curDate.format(formatter));
            }
        }

        return unhappyFridays.toString();
    }

    public String findNextFriday13(String stringDate) {
        LocalDate date;

        try {
            date = LocalDate.parse(stringDate);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Некорректная дата");
        }

        do {
            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } while (date.getDayOfMonth() != THIRTEEN);

        return date.format(formatter);
    }
}
