package edu.hw05.task3;

import java.time.LocalDate;
import java.util.Optional;

final class CDateParser3 implements IDateParser {
    public static final String DATE_PARSER_3_REGEX = "^(tomorrow)|(today)|(yesterday)$";
    private static final String TOMORROW_STRING = "tomorrow";
    private static final String TODAY_STRING = "today";
    private static final String YESTERDAY_STRING = "yesterday";

    @Override
    public Optional<LocalDate> parseDate(String string) {
        switch (string) {
            case TODAY_STRING -> {
                return Optional.of(LocalDate.now());
            }

            case TOMORROW_STRING -> {
                return Optional.of(LocalDate.now().plusDays(1));
            }

            case YESTERDAY_STRING -> {
                return Optional.of(LocalDate.now().minusDays(1));
            }

            default -> {
                return Optional.empty();
            }
        }
    }
}
