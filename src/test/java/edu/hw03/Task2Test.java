package edu.hw03;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class Task2Test {

    private static Stream<Arguments> correctStrings() {
        return Stream.of(
            Arguments.of("()()()", "[(), (), ()]"),
            Arguments.of("((()))", "[((()))]"),
            Arguments.of("((()))(())()()(()())", "[((())), (()), (), (), (()())]"),
            Arguments.of("((())())(()(()()))", "[((())()), (()(()()))]")
        );
    }


    @ParameterizedTest
    @MethodSource({"correctStrings"})
    @DisplayName("Тестирование корректных строк из тестов задания")
    void testFromTask(String input, String expected) {
        Assertions.assertThat(Task2.clusterize(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = { "()()(()))", "", "(((()", "()(()())(()(()" })
    @DisplayName("Тестирование некорректных строк")
    void testMethod(String input) {
        Assertions.assertThatThrownBy(() ->
                Task2.clusterize(input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
