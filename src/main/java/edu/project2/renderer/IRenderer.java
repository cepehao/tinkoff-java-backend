package edu.project2.renderer;

import edu.project2.model.CCoordinate;
import edu.project2.model.CMaze;
import java.util.List;

public interface IRenderer {
    String render(CMaze maze);

    String render(CMaze maze, List<CCoordinate> path);
}
