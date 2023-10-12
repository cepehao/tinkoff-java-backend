package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task8Test {

    @BeforeEach
    void setUp() {
        task8 = new Task8();
    }

    private Task8 task8;
    private int[][] board;
    private boolean actual;

    @Test
    @DisplayName("Метод должен возвращать IllegalArgumentException в ответ на не квадратную матрицу")
    void giveNotSquareMatrixShouldThrowIllegalArgumentException() {
        board = new int[][] {
            {0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
        };

        Assertions.assertThatThrownBy(() ->
                task8.knightBoardCapture(board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод должен возвращать IllegalArgumentException в ответ на матрицу 2х2")
    void give2x2MatrixShouldThrowIllegalArgumentException() {
        board = new int[][] {
            {0, 1},
            {1, 0}
        };

        Assertions.assertThatThrownBy(() ->
                task8.knightBoardCapture(board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод должен возвращать true в ответ на матрицу, на которой кони не смогут срубить")
    void giveCorrectMatrixShouldReturnTrue() {
        board = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        actual = task8.knightBoardCapture(board);

        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("Метод должен возвращать false в ответ на матрицу, на которой кони не могут срубить")
    void giveCorrectMatrixShouldReturnFalse() {
        board = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        actual = task8.knightBoardCapture(board);

        Assertions.assertThat(actual).isEqualTo(false);
    }

    @Test
    @DisplayName("Метод должен возвращать true в ответ на матрицу, нулевую матрицу 5х5")
    void giveZeroMatrix5x5ShouldReturnTrue() {
        board = new int[][] {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
        };

        actual = task8.knightBoardCapture(board);

        Assertions.assertThat(actual).isEqualTo(true);
    }
}
