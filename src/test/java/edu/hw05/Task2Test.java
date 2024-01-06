package edu.hw05;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task2Test {
    @BeforeEach
    void setUp() {
        task2 = new Task2();
    }

    private Task2 task2;

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> getUnhappyFridaysCorrectArgs() {
        return Stream.of(
            Arguments.of(1925, "[1925-02-13, 1925-03-13, 1925-11-13]"),
            Arguments.of(2024, "[2024-09-13, 2024-12-13]"),
            Arguments.of(2023, "[2023-01-13, 2023-10-13]")
        );
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> getUnhappyFridaysIncorrectArgs() {
        return Stream.of(
            Arguments.of(0),
            Arguments.of(123124235),
            Arguments.of(50),
            Arguments.of(-20)
        );
    }

    private static Stream<Arguments> findNextFriday13() {
        return Stream.of(
            Arguments.of("1925-02-13", "1925-03-13"),
            Arguments.of("2024-09-13", "2024-12-13"),
            Arguments.of("2023-01-13", "2023-10-13")
        );
    }

    @ParameterizedTest
    @MethodSource("getUnhappyFridaysCorrectArgs")
    void getUnhappyFridaysCorrect(int year, String expected) {
        var actual = task2.getUnhappyFridays(year);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getUnhappyFridaysIncorrectArgs")
    void getUnhappyFridaysIncorrect(int year) {
        Assertions.assertThatThrownBy(
            () -> task2.getUnhappyFridays(year)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("findNextFriday13")
    void findNextFriday13(String date, String expected) {
        Assertions.assertThat(task2.findNextFriday13(date)).isEqualTo(expected);
    }
}
