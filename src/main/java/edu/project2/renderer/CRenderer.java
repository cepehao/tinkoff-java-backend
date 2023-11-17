package edu.project2.renderer;

import edu.project2.model.CCell;
import edu.project2.model.CCoordinate;
import edu.project2.model.CMaze;
import java.util.List;

public final class CRenderer implements IRenderer {

    @Override
    public String render(CMaze maze) {
        var grid = maze.getGrid();
        var height = maze.getHeight();
        var width = maze.getWidth();

        return buildStringGrid(grid, height, width);
    }

    public String buildStringGrid(CCell[][] grid, int height, int width) {
        var sb = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String render(CMaze maze, List<CCoordinate> path) {
        var grid = maze.getGrid();
        var height = maze.getHeight();
        var width = maze.getWidth();

        return buildStringGrid(grid, height, width, path);
    }

    public String buildStringGrid(CCell[][] grid, int height, int width, List<CCoordinate> path) {
        var sb = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (path.contains(new CCoordinate(i, j))) {
                    grid[i][j] = CCell.PATH;
                }
                sb.append(grid[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
