package edu.project2.solver;

import edu.project2.model.CCoordinate;
import edu.project2.model.CMaze;
import java.util.List;

public interface ISolver {
    List<CCoordinate> solve(CMaze maze, CCoordinate start, CCoordinate end);
}
