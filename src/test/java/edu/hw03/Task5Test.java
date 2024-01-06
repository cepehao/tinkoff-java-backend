package edu.hw03;

import edu.hw03.Task5.CContact;
import edu.hw03.Task5.Task5;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task5Test {

    @BeforeEach
    void setUp() {
        task5 = new Task5();
    }

    private Task5 task5;

    @SuppressWarnings("MultipleStringLiterals")
    private static Stream<Arguments> correctArguments() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                "ASC",
                List.of(
                    new CContact("Thomas", "Aquinas"),
                    new CContact("Rene", "Descartes"),
                    new CContact("David", "Hume"),
                    new CContact("John", "Locke")
                )
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                "DESC",
                List.of(
                    new CContact("Carl", "Gauss"),
                    new CContact("Leonhard", "Euler"),
                    new CContact("Paul", "Erdos")
                )
            ),
            Arguments.of(
                new String[] {},
                "DESC",
                List.of()
            ),
            Arguments.of(
                null,
                "DESC",
                List.of()
            )
        );
    }

    private static Stream<Arguments> incorrectArguments() {
        return Stream.of(
            Arguments.of(
                null,
                "DESCCCC"
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                "AASC"
            )
        );
    }

    @ParameterizedTest
    @MethodSource({"correctArguments"})
    @DisplayName("Тестирование корректных входных данных")
    void testGoodData(String[] inputNames, String sort, List<CContact> expected) {
        Assertions.assertThat(task5.parseContacts(inputNames, sort)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource({"incorrectArguments"})
    @DisplayName("Тестирование некорректных входных данных")
    void testBadNumbers(String[] inputNames, String sort) {
        Assertions.assertThatThrownBy(() ->
                task5.parseContacts(inputNames, sort))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
