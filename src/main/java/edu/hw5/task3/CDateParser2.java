package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

final class CDateParser2 implements IDateParser {
    public static final String DATE_PARSER_2_REGEX = "\\d{1,2}/\\d{1,2}/\\d{1,4}";
    private static final DateTimeFormatter DATE_TIME_FORMATTER2 = DateTimeFormatter.ofPattern("d/M/y");

    @Override
    public Optional<LocalDate> parseDate(String string) {
        try {
            return Optional.of(LocalDate.parse(string, DATE_TIME_FORMATTER2));
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
    }
}
