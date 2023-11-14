package edu.project2.solver;

import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class SolverDFS implements SolverInterface {

    Stack<Coordinate> stack = new Stack<>();
    HashSet<Coordinate> visited = new HashSet<>();
    HashMap<Coordinate, Coordinate> parent = new HashMap<>();

    private void dfs(Maze maze, Coordinate current, Coordinate end) {
        visited.add(current);

        // проходимся по соседям
        if (maze.getGrid()[current.row() + 1][current.col()].type() == Type.PASSAGE
            && !visited.contains(new Coordinate(current.row() + 1, current.col()))) {
            Coordinate next = new Coordinate(current.row() + 1, current.col());
            parent.put(next, current);
            dfs(maze, next, end);
        }

        if (maze.getGrid()[current.row() - 1][current.col()].type() == Type.PASSAGE
            && !visited.contains(new Coordinate(current.row() - 1, current.col()))) {
            Coordinate next = new Coordinate(current.row() - 1, current.col());
            parent.put(next, current);
            dfs(maze, next, end);
        }

        if (maze.getGrid()[current.row()][current.col() + 1].type() == Type.PASSAGE
            && !visited.contains(new Coordinate(current.row(), current.col() + 1))) {
            Coordinate next = new Coordinate(current.row(), current.col() + 1);
            parent.put(next, current);
            dfs(maze, next, end);
        }

        if (maze.getGrid()[current.row()][current.col() - 1].type() == Type.PASSAGE
            && !visited.contains(new Coordinate(current.row(), current.col() - 1))) {
            Coordinate next = new Coordinate(current.row(), current.col() - 1);
            parent.put(next, current);
            dfs(maze, next, end);
        }
    }

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        stack.push(start);
        visited.add(start);

        dfs(maze, start, end);

        if (!visited.contains(end)) {
            return null;
        }

        return restorePath(start, end, parent);
    }
}
