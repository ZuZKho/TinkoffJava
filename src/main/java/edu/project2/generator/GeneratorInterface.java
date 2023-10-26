package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface GeneratorInterface {

    int THREE = 3;
    int FOUR = 4;

    default List<Coordinate> getNeighbours(int height, int width, HashSet<Coordinate> visited, Coordinate current) {
        List<Coordinate> neighbours = new ArrayList<>();
        if (current.row() >= THREE) {
            if (!visited.contains(new Coordinate(current.row() - 2, current.col()))) {
                neighbours.add(new Coordinate(current.row() - 2, current.col()));
            }
        }

        if (current.col() >= THREE) {
            if (!visited.contains(new Coordinate(current.row(), current.col() - 2))) {
                neighbours.add(new Coordinate(current.row(), current.col() - 2));
            }
        }

        if (current.row() <= height - FOUR) {
            if (!visited.contains(new Coordinate(current.row() + 2, current.col()))) {
                neighbours.add(new Coordinate(current.row() + 2, current.col()));
            }
        }

        if (current.col() <= width - FOUR) {
            if (!visited.contains(new Coordinate(current.row(), current.col() + 2))) {
                neighbours.add(new Coordinate(current.row(), current.col() + 2));
            }
        }
        return neighbours;
    }

    private Maze generateTemplate(int height, int width) {
        Cell[][] field = new Cell[height][width];

        for (int i = 0; i < height; i += 1) {
            for (int j = 0; j < width; j += 1) {
                if (i % 2 == 0 || j % 2 == 0) {
                    field[i][j] = new Cell(i, j, Cell.Type.WALL);
                } else {
                    field[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
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

