package edu.hw03;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task3Test {

    @SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
    private static Stream<Arguments> arguments() {

        return Stream.of(
            Arguments.of(
                Arrays.asList("a", "bb", "a", "bb"),
                Map.of("bb", 2, "a", 2)
            ),
            Arguments.of(
                Arrays.asList("this", "and", "that", "and"),
                Map.of("that", 1, "and", 2, "this", 1)
            ),
            Arguments.of(
                Arrays.asList("код", "код", "код", "bug"),
                Map.of("код", 3, "bug", 1)
            ),
            Arguments.of(
                Arrays.asList(1, 1, 2, 2),
                Map.of(1, 2, 2, 2)
            )
        );
    }

    @ParameterizedTest
    @MethodSource({"arguments"})
    @DisplayName("Тестирование из задания")
    <T> void testsFromExample(List<T> input, Map<T, Integer> expected) {
        Assertions.assertThat(Task3.freqDict(input)).isEqualTo(expected);
    }
}
