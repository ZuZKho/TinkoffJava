package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface GeneratorInterface {

    int MINCOORDINATE = 1;
    int STEP = 2;

    default List<Coordinate> getNeighbours(int height, int width, HashSet<Coordinate> visited, Coordinate current) {
        List<Coordinate> neighbours = new ArrayList<>();
        if (current.row() >= MINCOORDINATE + STEP) {
            if (!visited.contains(new Coordinate(current.row() - STEP, current.col()))) {
                neighbours.add(new Coordinate(current.row() - STEP, current.col()));
            }
        }

        if (current.col() >= MINCOORDINATE + STEP) {
            if (!visited.contains(new Coordinate(current.row(), current.col() - STEP))) {
                neighbours.add(new Coordinate(current.row(), current.col() - STEP));
            }
        }

        if (current.row() <= height - 1 - MINCOORDINATE - STEP) {
            if (!visited.contains(new Coordinate(current.row() + STEP, current.col()))) {
                neighbours.add(new Coordinate(current.row() + STEP, current.col()));
            }
        }

        if (current.col() <= width - 1 - MINCOORDINATE - STEP) {
            if (!visited.contains(new Coordinate(current.row(), current.col() + STEP))) {
                neighbours.add(new Coordinate(current.row(), current.col() + STEP));
            }
        }
        return neighbours;
    }

    private Maze generateTemplate(int height, int width) {
        Cell[][] field = new Cell[height][width];

        for (int i = 0; i < height; i += 1) {
            for (int j = 0; j < width; j += 1) {
                if (i % 2 == 0 || j % 2 == 0) {
                    field[i][j] = new Cell(i, j, Type.WALL);
                } else {
                    field[i][j] = new Cell(i, j, Type.PASSAGE);
                }
            }
        }

        return new Maze(height, width, field);
    }

    default Maze generate(int height, int width) {
        if (height % 2 == 0 || width % 2 == 0) {
            throw new IllegalArgumentException("Arguments must be odd!");
        }
        Maze maze = generateTemplate(height, width);

        return generateMazeFromTemplate(maze);
    }

    Maze generateMazeFromTemplate(Maze maze);
}

