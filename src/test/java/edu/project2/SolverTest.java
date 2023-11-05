package edu.project2;

import edu.project2.model.CCell;
import edu.project2.model.CCoordinate;
import edu.project2.model.CMaze;
import edu.project2.solver.CFactorySolver;
import edu.project2.solver.ISolver;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SolverTest {
    private static final int HEIGHT = 7;
    private static final int WIDTH = 7;

    private static final CCoordinate START = new CCoordinate(1, 1);
    private static final CCoordinate END = new CCoordinate(5, 5);

    private static final CCell[][] GRID = new CCell[][]
        {
            {CCell.WALL, CCell.WALL, CCell.WALL, CCell.WALL, CCell.WALL, CCell.WALL, CCell.WALL},
            {CCell.WALL, CCell.PASSAGE, CCell.WALL, CCell.PASSAGE, CCell.WALL, CCell.PASSAGE, CCell.WALL},
            {CCell.WALL, CCell.PASSAGE, CCell.WALL, CCell.PASSAGE, CCell.WALL, CCell.PASSAGE, CCell.WALL},
            {CCell.WALL, CCell.PASSAGE, CCell.WALL, CCell.PASSAGE, CCell.WALL, CCell.PASSAGE, CCell.WALL},
            {CCell.WALL, CCell.PASSAGE, CCell.WALL, CCell.PASSAGE, CCell.WALL, CCell.PASSAGE, CCell.WALL},
            {CCell.WALL, CCell.PASSAGE, CCell.PASSAGE, CCell.PASSAGE, CCell.PASSAGE, CCell.PASSAGE, CCell.WALL},
            {CCell.WALL, CCell.WALL, CCell.WALL, CCell.WALL, CCell.WALL, CCell.WALL, CCell.WALL}
        };

    @SuppressWarnings("MagicNumber")
    private static final Set<CCoordinate> EXPECTED_PATH = Set.of(
        new CCoordinate(1, 1),
        new CCoordinate(2, 1),
        new CCoordinate(3, 1),
        new CCoordinate(4, 1),
        new CCoordinate(5, 1),
        new CCoordinate(5, 2),
        new CCoordinate(5, 3),
        new CCoordinate(5, 4),
        new CCoordinate(5, 5)
    );

    private static Stream<Arguments> solverType() {
        return Stream.of(
            Arguments.of("BFS"),
            Arguments.of("DFS")
        );
    }

    @ParameterizedTest
    @MethodSource("solverType")
    @DisplayName("Решение")
    void correctData(String solvedType) {
        var maze = new CMaze(HEIGHT, WIDTH, GRID);

        ISolver solver = CFactorySolver.getSolver(solvedType);

        Set<CCoordinate> actual = new HashSet<>(solver.solve(maze, START, END));

        assertThat(actual).isEqualTo(EXPECTED_PATH);
    }
}
