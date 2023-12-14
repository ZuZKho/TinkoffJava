package edu.project2.solver;

import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Type;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class SolverBFS implements SolverInterface {

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Queue<Coordinate> queue = new ArrayDeque<>();
        HashSet<Coordinate> visited = new HashSet<>();
        HashMap<Coordinate, Coordinate> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (maze.getGrid()[current.row() + 1][current.col()].type() == Type.PASSAGE
                && !visited.contains(new Coordinate(current.row() + 1, current.col()))) {
                Coordinate next = new Coordinate(current.row() + 1, current.col());
                parent.put(next, current);
                visited.add(next);
                queue.add(next);
            }

            if (maze.getGrid()[current.row() - 1][current.col()].type() == Type.PASSAGE
                && !visited.contains(new Coordinate(current.row() - 1, current.col()))) {
                Coordinate next = new Coordinate(current.row() - 1, current.col());
                parent.put(next, current);
                visited.add(next);
                queue.add(next);
            }

            if (maze.getGrid()[current.row()][current.col() + 1].type() == Type.PASSAGE
                && !visited.contains(new Coordinate(current.row(), current.col() + 1))) {
                Coordinate next = new Coordinate(current.row(), current.col() + 1);
                parent.put(next, current);
                visited.add(next);
                queue.add(next);
            }

            if (maze.getGrid()[current.row()][current.col() - 1].type() == Type.PASSAGE
                && !visited.contains(new Coordinate(current.row(), current.col() - 1))) {
                Coordinate next = new Coordinate(current.row(), current.col() - 1);
                parent.put(next, current);
                visited.add(next);
                queue.add(next);
            }
        }

        if (!visited.contains(end)) {
            return null;
        }

        return restorePath(start, end, parent);
    }
}
