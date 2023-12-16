package edu.hw7.task1;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CounterTest {
    private Counter counter;

    @BeforeEach
    void setUp() {
        counter = new Counter();
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> correctValues() {
        return Stream.of(
            Arguments.of(100, 200),
            Arguments.of(0, 0),
            Arguments.of(50, 100),
            Arguments.of(10000, 20000),
            Arguments.of(7000, 14000),
            Arguments.of(12345, 24690),
            Arguments.of(1000000, 2000000)
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
    void correctValues(int n, int expected) {
        counter.work(n);

        var actual = counter.getCounter();

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("incorrectValues")
    void incorrectValues(int n) {
        Assertions.assertThatThrownBy(
            () -> counter.work(n)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
