package edu.hw3;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class Task4Test {

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> arguments() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(2465, "MMCDLXV"),
            Arguments.of(1, "I"),
            Arguments.of(3999, "MMMCMXCIX"),
            Arguments.of(1000, "M")
        );
    }

    @ParameterizedTest
    @MethodSource({"arguments"})
    @DisplayName("Тестирование корректных чисел")
    void testGoodNumbers(int input, String expected) {
        Assertions.assertThat(Task4.convertToRoman(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4000, 9999, -5})
    @DisplayName("Тестирование некорректных чисел")
    void testMethod(int input) {
        Assertions.assertThatThrownBy(() ->
                Task4.convertToRoman(input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
