package edu.project2;

import edu.project2.generator.CGeneratorPrim;
import edu.project2.generator.IGenerator;
import edu.project2.model.CCoordinate;
import edu.project2.model.CMaze;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GeneratorTest {

    private IGenerator generatorPrim;

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> correctCoordinates() {
        return Stream.of(
            Arguments.of(7, 7),
            Arguments.of(5, 13),
            Arguments.of(7, 5)
        );
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> incorrectCoordinates() {
        return Stream.of(
            Arguments.of(0, 10),
            Arguments.of(5, -12),
            Arguments.of(3, 3),
            Arguments.of(1, 1)
        );
    }

    @SuppressWarnings("MagicNumber")
    private static boolean allHasAtLeastOneNeighbor(CMaze maze) {
        var height = maze.getHeight();
        var width = maze.getWidth();

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                var neighbor = maze.getNeighbors(new CCoordinate(i, j));

                if (neighbor.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    @BeforeEach
    void setUp() {
        generatorPrim = new CGeneratorPrim();
    }

    @ParameterizedTest
    @MethodSource("correctCoordinates")
    @DisplayName("В идеальном лабиринте из любой точки пути можно добраться до любой")
    void correctData(int height, int width) {
        var maze = generatorPrim.generate(height, width);

        assertThat(allHasAtLeastOneNeighbor(maze)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("incorrectCoordinates")
    @DisplayName("Генерация с некорректными данными")
    void incorrectData(int height, int width) {
        assertThatThrownBy(
            () -> generatorPrim.generate(height, width)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
