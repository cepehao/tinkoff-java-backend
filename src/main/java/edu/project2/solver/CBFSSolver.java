package edu.project2.solver;

import edu.project2.model.CCoordinate;
import edu.project2.model.CMaze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public final class CBFSSolver implements ISolver {

    public List<CCoordinate> solve(CMaze maze, CCoordinate start, CCoordinate end) {
        return findPath(maze, start, end);
    }

    private List<CCoordinate> findPath(CMaze maze, CCoordinate start, CCoordinate end) {
        Queue<CCoordinate> queue = new LinkedList<>();
        Map<CCoordinate, CCoordinate> parentMap = new HashMap<>();

        queue.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            if (current.equals(end)) {
                return buildPath(parentMap, current);
            }
            for (var neighbor : maze.getNeighbors(current)) {
                if (!parentMap.containsKey(neighbor)) {
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return new ArrayList<>();
    }

    private List<CCoordinate> buildPath(Map<CCoordinate, CCoordinate> parentMap, CCoordinate current) {
        List<CCoordinate> path = new ArrayList<>();
        var newCurrent = current;
        while (newCurrent != null) {
            path.add(newCurrent);
            newCurrent = parentMap.get(newCurrent);
        }
        Collections.reverse(path);
        return path;
    }
}
