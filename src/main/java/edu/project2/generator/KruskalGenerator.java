package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class KruskalGenerator implements GeneratorInterface {

    public Maze generateMazeFromTemplate(Maze maze) {
        List<Pair<Coordinate, Coordinate>> edges = new ArrayList<>();

        int height = maze.getHeight();
        int width = maze.getWidth();
        Cell[][] field = maze.getGrid();

        for (int i = 1; i < height - 1; i += 2) {
            for (int j = 1; j < width - 1; j += 2) {
                var current = new Coordinate(i, j);
                var neighbours = getNeighbours(height, width, new HashSet<>(), current);
                for (var neighbour : neighbours) {
                    edges.add(new Pair<>(current, neighbour));
                }
            }
        }

        Random randomGenerator = new Random();
        Collections.shuffle(edges, randomGenerator);

        DSU<Coordinate> dsu = new DSU<>();

        for (var edge : edges) {
            Coordinate first = edge.first;
            Coordinate second = edge.second;
            if (!dsu.find(first).equals(dsu.find(second))) {
                dsu.unite(first, second);

                // Ломаем стену
                field[(first.row() + second.row()) / 2][(first.col() + second.col()) / 2] =
                    new Cell((first.row() + second.row()) / 2, (first.col() + second.col()) / 2, Type.PASSAGE);
            }
        }

        return new Maze(height, width, field);
    }

}
