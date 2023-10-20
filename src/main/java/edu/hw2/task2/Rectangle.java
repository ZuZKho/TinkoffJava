package edu.hw2.task2;

public class Rectangle {
    private final Integer width;
    private final Integer height;

    Rectangle() {
        this.width = null;
        this.height = null;
    }

    Rectangle(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    Rectangle setWidth(Integer width) {
        return new Rectangle(width, this.height);
    }

    Rectangle setHeight(Integer height) {
        return new Rectangle(this.width, height);
    }

    double area() {
        if (this.width == null || this.height == null) {
            return 0;
        }
        return width * height;
    }
}


