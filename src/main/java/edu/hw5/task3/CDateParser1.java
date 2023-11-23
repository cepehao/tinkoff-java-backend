package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

final class CDateParser1 implements IDateParser {
    public static final String DATE_PARSER_1_REGEX = "\\d{1,4}-\\d{1,2}-\\d{1,2}";
    private static final DateTimeFormatter DATE_TIME_FORMATTER1 = DateTimeFormatter.ofPattern("y-M-d");

    @Override
    public Optional<LocalDate> parseDate(String string) {
        try {
            return Optional.of(LocalDate.parse(string, DATE_TIME_FORMATTER1));
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
    }
}
