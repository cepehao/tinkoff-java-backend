package edu.hw05;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task6Test {

    @SuppressWarnings("MultipleStringLiterals")
    private static Stream<Arguments> substring() {
        return Stream.of(
            Arguments.of("abc", "achfdbaabgabcaabg"),
            Arguments.of("12345", "0123456"),
            Arguments.of("", ""),
            Arguments.of("hello world", "he say hello world!!!"),
            Arguments.of("two", "one two three four"),
            Arguments.of("()**", "ase23 wqr11 asd __fds f w rt u()**gfhj "),
            Arguments.of("ABC", "ABC"),
            Arguments.of("...", "How... Are u okey?"),
            Arguments.of("", "fsdf klgdfg DEW213 fdksd."),
            Arguments.of("!", "Do  it quickly! Are u understand?")
        );
    }

    private static Stream<Arguments> notSubstring() {
        return Stream.of(
            Arguments.of("abc", "bca"),
            Arguments.of("213", "jsdjf 23 dllsd"),
            Arguments.of("helo world", "he say hello world!!!"),
            Arguments.of("one two three four five", "one three five"),
            Arguments.of("!?", "Do  it quickly! Are u understand?")
        );
    }

    @ParameterizedTest
    @MethodSource("substring")
    @DisplayName("Тестирование строк, являющихся подстрокой")
    void giveSubstringShouldReturnTrue(String substring, String string) {
        var actual = Task6.isSubstring(substring, string);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("notSubstring")
    @DisplayName("Тестирование строк, не являющихся подстрокой")
    void giveNotSubstringShouldReturnFalse(String substring, String string) {
        var actual = Task6.isSubstring(substring, string);

        Assertions.assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Тестирование null")
    void giveNullShouldThrowIllegalArgumentsException() {
        Assertions.assertThatThrownBy(
            () -> Task6.isSubstring(null, null)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
