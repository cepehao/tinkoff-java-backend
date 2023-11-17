package edu.project2.model;

import java.util.ArrayList;
import java.util.List;

public final class CMaze {
    private final int height;
    private final int width;
    private final CCell[][] grid;

    private static final int MAX_SIZE = 4;

    public CMaze(int height, int width, CCell[][] grid) {

        if (height < MAX_SIZE || width < MAX_SIZE) {
            throw new IllegalArgumentException("Некорректная размерность поля");
        }

        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public CCell[][] getGrid() {
        return grid;
    }

    public List<CCoordinate> getNeighbors(CCoordinate coordinate) {
        int row = coordinate.row();
        int col = coordinate.col();
        List<CCoordinate> neighbors = new ArrayList<>();

        if (isValidCoordinate(row - 1, col)) {
            neighbors.add(new CCoordinate(row - 1, col));
        }
        if (isValidCoordinate(row + 1, col)) {
            neighbors.add(new CCoordinate(row + 1, col));
        }
        if (isValidCoordinate(row, col - 1)) {
            neighbors.add(new CCoordinate(row, col - 1));
        }
        if (isValidCoordinate(row, col + 1)) {
            neighbors.add(new CCoordinate(row, col + 1));
        }

        return neighbors;
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width && grid[row][col].isPassage();
    }
}
