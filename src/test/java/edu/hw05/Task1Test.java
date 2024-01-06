package edu.hw05;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task1Test {

    @SuppressWarnings("MultipleStringLiterals")
    private static Stream<Arguments> correctDates() {
        return Stream.of(
            Arguments.of(
                new String[] {
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-03-12, 15:00 - 2022-03-12, 17:00",
                    "2022-03-12, 23:30 - 2022-03-13, 00:30"
                },
                "2ч 10м"

            ),
            Arguments.of(
                new String[] {
                    "2022-03-12, 20:20 - 2022-03-12, 20:30",
                    "2022-03-12, 20:40 - 2022-03-12, 20:50"
                },
                "0ч 10м"
            ),
            Arguments.of(
                new String[] {
                    "2020-05-14, 12:30 - 2020-05-14, 14:00",
                    "2022-03-12, 23:30 - 2022-03-13, 01:00",
                },
                "1ч 30м"
            )
        );
    }

    private static Stream<Arguments> incorrectDates() {
        return Stream.of(
            Arguments.of(
                    (Object) new String[] {
                        "2022-03-35, 20:20 - 2022-03-12, 23:50",
                        "2022-03-12, 15:00 - 2022-03-12, 17:00"
                    }
            ),
            Arguments.of(
                    (Object) new String[] {
                        "20222-03-12, 20:20 - 2022-03-12, 23:50",
                        "2022-03-12, 15:00 | 2022-03-12, 17:00",
                        "2022-03-12 - 2022-03-13, 00:30",
                        "",
                        "fsdfdsfssdf",
                        "234233"
                    }
            )
        );
    }

    @ParameterizedTest
    @MethodSource("correctDates")
    void giveCorrectDatesShouldReturnTrue(String[] correctDates, String expected) {
        Assertions.assertThat(Task1.avgTime(correctDates)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("incorrectDates")
    void giveIncorrectDatesShouldThrowException(String[] correctDates) {
        Assertions.assertThatThrownBy(
            () -> Task1.avgTime(correctDates)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
