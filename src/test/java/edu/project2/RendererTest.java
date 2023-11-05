package edu.project2;

import edu.project2.model.CCell;
import edu.project2.model.CCoordinate;
import edu.project2.renderer.CRenderer;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RendererTest {
    private CRenderer renderer;

    private static final int HEIGHT = 7;
    private static final int WIDTH = 7;

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

    private static final List<CCoordinate> PATH = List.of(
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

    @SuppressWarnings("MultipleStringLiterals")
    private static final String EXPECTED_STRING_WITHOUT_PATH =
        "x x x x x x x " + System.lineSeparator()
            + "x + x + x + x " + System.lineSeparator()
            + "x + x + x + x " + System.lineSeparator()
            + "x + x + x + x " + System.lineSeparator()
            + "x + x + x + x " + System.lineSeparator()
            + "x + + + + + x " + System.lineSeparator()
            + "x x x x x x x " + System.lineSeparator();

    @SuppressWarnings("MultipleStringLiterals")
    private static final String EXPECTED_STRING_WITH_PATH =
        "x x x x x x x " + System.lineSeparator()
            + "x " + "\u001B[31m+\u001B[0m" + " x + x + x " + System.lineSeparator()
            + "x " + "\u001B[31m+\u001B[0m" + " x + x + x " + System.lineSeparator()
            + "x " + "\u001B[31m+\u001B[0m" + " x + x + x " + System.lineSeparator()
            + "x " + "\u001B[31m+\u001B[0m" + " x + x + x " + System.lineSeparator()
            + "x " + "\u001B[31m+\u001B[0m " + "\u001B[31m+\u001B[0m " + "\u001B[31m+\u001B[0m "
            + "\u001B[31m+\u001B[0m " + "\u001B[31m+\u001B[0m" + " x " + System.lineSeparator()
            + "x x x x x x x " + System.lineSeparator();

    @BeforeEach
    void setUp() {
        renderer = new CRenderer();
    }

    @Test
    @DisplayName("Рендеринг без решения")
    void testWithoutSolve() {
        var actual = renderer.buildStringGrid(GRID, HEIGHT, WIDTH);

        assertThat(actual).isEqualTo(EXPECTED_STRING_WITHOUT_PATH);
    }

    @Test
    @DisplayName("Рендеринг с решением")
    void testWithSolve() {
        var actual = renderer.buildStringGrid(GRID, HEIGHT, WIDTH, PATH);

        assertThat(actual).isEqualTo(EXPECTED_STRING_WITH_PATH);
    }

}
