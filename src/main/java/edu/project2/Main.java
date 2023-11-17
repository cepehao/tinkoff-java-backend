package edu.project2;

import edu.project2.generator.CGeneratorPrim;
import edu.project2.model.CCoordinate;
import edu.project2.renderer.CRenderer;
import edu.project2.solver.CBFSSolver;

public final class Main {

    private Main() {

    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        var primaGenerator = new CGeneratorPrim();

        var maze = primaGenerator.generate(7, 7);

        var renderer = new CRenderer();

        System.out.println(renderer.render(maze));

        var solver = new CBFSSolver();

        var solve = solver.solve(maze, new CCoordinate(1, 1), new CCoordinate(5, 5));

        System.out.println(renderer.render(maze, solve));
    }
}
