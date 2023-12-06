package edu.hw5;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task4Test {

    private static Stream<Arguments> correctPasswords() {
        return Stream.of(
            Arguments.of("qwer!1234"),
            Arguments.of("~"),
            Arguments.of("!"),
            Arguments.of("@"),
            Arguments.of("#"),
            Arguments.of("%"),
            Arguments.of("^"),
            Arguments.of("&"),
            Arguments.of("*"),
            Arguments.of("|"),
            Arguments.of("hello WORLD!"),
            Arguments.of("%pass%")
        );
    }

    private static Stream<Arguments> incorrectPasswords() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("hello"),
            Arguments.of("fsdkfs 4234 lsdf; ()))) __++="),
            Arguments.of("my_name_is"),
            Arguments.of("DSerGDFG"),
            Arguments.of("                   ")
        );
    }


    @ParameterizedTest
    @MethodSource("correctPasswords")
    @DisplayName("Тестирование паролей с специальными символами")
    void giveCorrectShouldReturnTrue(String password) {
        var actual = Task4.isValidPassword(password);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("incorrectPasswords")
    @DisplayName("Тестирование паролей без специальных символов")
    void giveIncorrectShouldReturnFalse(String password) {
        var actual = Task4.isValidPassword(password);

        Assertions.assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Тестирование null")
    void giveNullShouldThrowIllegalArgumentsException() {
        String actual = null;

        Assertions.assertThatThrownBy(
            () -> Task4.isValidPassword(actual)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
