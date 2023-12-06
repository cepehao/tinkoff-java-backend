package edu.hw5;

import edu.hw5.task3.CDateParserFactory;
import edu.hw5.task3.IDateParser;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task3Test {
    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> dates() {
        return Stream.of(
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, 12, 2))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(20, 3, 1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234))),
            Arguments.of("2234 ago", Optional.empty()),
            Arguments.of("11.11.2023", Optional.empty()),
            Arguments.of("2020-15-35", Optional.empty()),
            Arguments.of("", Optional.empty()),
            Arguments.of("hello", Optional.empty())
        );
    }

    @ParameterizedTest
    @MethodSource("dates")
    void giveCorrectDates(String stringDate, Optional<LocalDate> expected) {
        IDateParser dateParser = CDateParserFactory.getDateParser(stringDate);
        Assertions.assertThat(dateParser.parseDate(stringDate)).isEqualTo(expected);
    }
}
