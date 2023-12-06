package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class CDateParser4 implements IDateParser {
    public static final String DATE_PARSER_4_REGEX = "^(1 day ago)|(([1-9]) days ago)|(([1-9][0-9]+) days ago)$";

    @Override
    @SuppressWarnings("MagicNumber")
    public Optional<LocalDate> parseDate(String string) {
        Pattern pattern = Pattern.compile(DATE_PARSER_4_REGEX);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            if (matcher.group(1) != null) {
                return Optional.of(LocalDate.now().minusDays(1));
            }

            if (matcher.group(2) != null) {
                return Optional.of(LocalDate.now().minusDays(Integer.parseInt(matcher.group(3))));
            }

            if (matcher.group(4) != null) {
                return Optional.of(LocalDate.now().minusDays(Integer.parseInt(matcher.group(5))));
            }
        }

        return Optional.empty();
    }
}
