package edu.project2.solver;

import edu.project2.model.CCoordinate;
import edu.project2.model.CMaze;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class CDFSSolver implements ISolver {
    @Override
    public List<CCoordinate> solve(CMaze maze, CCoordinate start, CCoordinate end) {
        return findPath(maze, start, end);
    }

    public List<CCoordinate> findPath(CMaze maze, CCoordinate start, CCoordinate end) {
        Set<CCoordinate> visited = new HashSet<>();
        visited.add(start);

        List<CCoordinate> path = buildPath(maze, start, end, visited);
        return Objects.requireNonNullElseGet(path, ArrayList::new);
    }

    private List<CCoordinate> buildPath(CMaze maze, CCoordinate current, CCoordinate end, Set<CCoordinate> visited) {
        if (current.equals(end)) {
            List<CCoordinate> path = new ArrayList<>();
            path.add(current);
            return path;
        }

        for (CCoordinate neighbor : maze.getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                List<CCoordinate> path = buildPath(maze, neighbor, end, visited);

                if (path != null) {
                    path.add(current);
                    return path;
                }
            }
        }
        return null;
    }
}
