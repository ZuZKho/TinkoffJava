package edu.project2.renderer;

import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.List;

public interface RendererInterface {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
