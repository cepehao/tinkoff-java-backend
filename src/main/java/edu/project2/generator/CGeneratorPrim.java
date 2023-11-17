package edu.project2.generator;

import edu.project2.model.CCoordinate;
import edu.project2.model.CMaze;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.utils.CGeneratorPrimaUtils.RANDOM;
import static edu.project2.utils.CGeneratorPrimaUtils.STEPS;
import static edu.project2.utils.CGeneratorPrimaUtils.initializeGrid;
import static edu.project2.utils.CGeneratorPrimaUtils.initializeNotCheckedList;
import static edu.project2.utils.CGeneratorPrimaUtils.makePassage;

public final class CGeneratorPrim implements IGenerator {
    @Override
    public CMaze generate(int height, int width) {
        if (height % 2 == 0 || width % 2 == 0) {
            throw new IllegalArgumentException("Ширина и высота не должны быть четными");
        }

        var grid = initializeGrid(height, width);

        var startY = 1 + 2 * RANDOM.nextInt((height - 1) / 2);
        var startX = 1 + 2 * RANDOM.nextInt((width - 1) / 2);
        makePassage(startY, startX, grid);

        var startCoordinate = new CCoordinate(startY, startX);
        List<CCoordinate> notChecked = initializeNotCheckedList(startCoordinate, height, width);

        while (!notChecked.isEmpty()) {
            var randomIndex = RANDOM.nextInt(notChecked.size());
            var coordinate = notChecked.remove(randomIndex);

            var randomY = coordinate.row();
            var randomX = coordinate.col();

            if (grid[randomY][randomX].isPassage()) {
                continue;
            }

            makePassage(randomY, randomX, grid);

            List<CCoordinate> passages = new ArrayList<>();

            for (var step : STEPS) {
                var y = randomY + step[0];
                var x = randomX + step[1];
                if (y >= 0 && y < height && x >= 0 && x < width) {
                    if (grid[y][x].isPassage()) {
                        passages.add(new CCoordinate(randomY + (step[0] / 2), randomX + (step[1] / 2)));
                    } else {
                        notChecked.add(new CCoordinate(y, x));
                    }
                }
            }

            var passage = passages.get(RANDOM.nextInt(passages.size()));
            makePassage(passage.row(), passage.col(), grid);
        }

        return new CMaze(height, width, grid);
    }
}
