package edu.hw07.task2;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FactorialTest {

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> correctValues() {
        return Stream.of(
            Arguments.of(6, 720L),
            Arguments.of(1, 1L),
            Arguments.of(15, 1307674368000L),
            Arguments.of(20, 2432902008176640000L),
            Arguments.of(8, 40320L)
        );
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> incorrectValues() {
        return Stream.of(
            Arguments.of(-100),
            Arguments.of(-2324),
            Arguments.of(-77777),
            Arguments.of(-1)
        );
    }

    @ParameterizedTest
    @MethodSource("correctValues")
    void correctValues(int n, long expected) {
        var actual = Factorial.calculateFactorial(n);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("incorrectValues")
    void incorrectValues(int n) {
        Assertions.assertThatThrownBy(
            () -> Factorial.calculateFactorial(n)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
