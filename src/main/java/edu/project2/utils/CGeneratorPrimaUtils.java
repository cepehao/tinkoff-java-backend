package edu.project2.utils;

import edu.project2.model.CCell;
import edu.project2.model.CCoordinate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class CGeneratorPrimaUtils {
    public static final Random RANDOM = new Random();

    public static final int[][] STEPS = new int[][] {
        {-2, 0}, {0, 2}, {2, 0}, {0, -2}
    };

    private CGeneratorPrimaUtils() {

    }

    public static List<CCoordinate> initializeNotCheckedList(CCoordinate startCoordinate, int height, int width) {
        List<CCoordinate> notChecked = new LinkedList<>();

        for (var step : STEPS) {
            var y = startCoordinate.row() + step[0];
            var x = startCoordinate.col() + step[1];
            if (y >= 0 && y < height && x >= 0 && x < width) {
                notChecked.add(new CCoordinate(y, x));
            }
        }

        return notChecked;
    }

    public static CCell[][] initializeGrid(int height, int width) {
        var grid = new CCell[height][width];

        for (var i = 0; i < height; i++) {
            for (var j = 0; j < width; j++) {
                grid[i][j] = CCell.WALL;
            }
        }

        return grid;
    }

    public static void makePassage(int row, int col, CCell[][] grid) {
        grid[row][col] = CCell.PASSAGE;
    }
}
