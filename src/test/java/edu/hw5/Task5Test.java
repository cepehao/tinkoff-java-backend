package edu.hw5;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task5Test {
    private static Stream<Arguments> correctNumbers() {
        return Stream.of(
            Arguments.of("А123ВЕ777"),
            Arguments.of("О777ОО177"),
            Arguments.of("Е390ОА59"),
            Arguments.of("Е100ЕЕ159"),
            Arguments.of("А999РТ07"),
            Arguments.of("Р123РТ100"),
            Arguments.of("А456РР110")
        );
    }

    private static Stream<Arguments> incorrectNumbers() {
        return Stream.of(
            Arguments.of("123АВЕ777"),
            Arguments.of("А123ВГ77"),
            Arguments.of("А123ВЕ7777"),
            Arguments.of("А000БВ159"),
            Arguments.of("С123ЕЕ000"),
            Arguments.of("Н777РР012"),
            Arguments.of("Н555НН001"),
            Arguments.of("А456ММ5"),
            Arguments.of(""),
            Arguments.of("12344567"),
            Arguments.of("аывпвар"),
            Arguments.of("е390ОА159"),
            Arguments.of("В999аа777"),
            //Латинские буквы
            Arguments.of("B999AA777")
        );
    }

    @ParameterizedTest
    @MethodSource("correctNumbers")
    @DisplayName("Тестирование корректных гос номеров")
    void giveCorrectShouldReturnTrue(String password) {
        var actual = Task5.isValidStateNumber(password);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("incorrectNumbers")
    @DisplayName("Тестирование некорректных гос номеров")
    void giveIncorrectShouldReturnFalse(String password) {
        var actual = Task5.isValidStateNumber(password);

        Assertions.assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Тестирование null")
    void giveNullShouldThrowIllegalArgumentsException() {
        String actual = null;

        Assertions.assertThatThrownBy(
            () -> Task5.isValidStateNumber(actual)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
