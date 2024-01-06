package edu.hw01;

/**************************************************************************************************************
 * ДЗ 1 задача 8. Кони на доске.
 * <p>
 * Напишите функцию, которая возвращает true, если кони расставлены на шахматной доске так,
 * что ни один конь не может захватить другого коня.
 * На вход подаётся двумерный массив размера 8х8, где 0 означает пустую клетку, а 1 - занятую конём клетку.
 * <p>
 * Примеры:
 * knightBoardCapture([
 *   [0, 0, 0, 1, 0, 0, 0, 0],
 *   [0, 0, 0, 0, 0, 0, 0, 0],
 *   [0, 1, 0, 0, 0, 1, 0, 0],
 *   [0, 0, 0, 0, 1, 0, 1, 0],
 *   [0, 1, 0, 0, 0, 1, 0, 0],
 *   [0, 0, 0, 0, 0, 0, 0, 0],
 *   [0, 1, 0, 0, 0, 0, 0, 1],
 *   [0, 0, 0, 0, 1, 0, 0, 0]
 * ]) -> true
 * knightBoardCapture([
 *   [1, 0, 1, 0, 1, 0, 1, 0],
 *   [0, 1, 0, 1, 0, 1, 0, 1],
 *   [0, 0, 0, 0, 1, 0, 1, 0],
 *   [0, 0, 1, 0, 0, 1, 0, 1],
 *   [1, 0, 0, 0, 1, 0, 1, 0],
 *   [0, 0, 0, 0, 0, 1, 0, 1],
 *   [1, 0, 0, 0, 1, 0, 1, 0],
 *   [0, 0, 0, 1, 0, 1, 0, 1]
 * ]) -> false
 *************************************************************************************************************/

public final class Task8 {

    private static final int MIN_MATRIX_SIZE = 3;

    private boolean canMove(int length, int x, int y) {
        return x >= 0 && x < length && y >= 0 && y < length;
    }

    @SuppressWarnings("MagicNumber")
    private boolean canCapture(int[][] board, int i, int j) {

        var possibleMoves = new int[][] {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

        for (var move : possibleMoves) {
            var x = i + move[0];
            var y = j + move[1];

            if (canMove(board.length, x, y) && board[x][y] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean knightBoardCapture(int[][] board) {
        var length = board.length;

        // Если матрица не квадратная - выбрасываем исключение.
        // Если размерность меньше трех - невозможно разместить коней так,
        // чтобы была возможность хотя бы кому-то срубить кого-то.
        if (length != board[0].length || length < MIN_MATRIX_SIZE) {
            throw new IllegalArgumentException();
        }

        for (var i = 0; i < length; i++) {
            for (var j = 0; j < length; j++) {
                if (board[i][j] == 1) {
                    if (canCapture(board, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
