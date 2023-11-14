package edu.project2;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Cell[][] getGrid() {
        return this.grid;
    }
}

