package edu.hw2.task2;

public class Square extends Rectangle {

    @Override
    Rectangle setWidth(Integer width) {
        return new Rectangle(width, width);
    }

    @Override
    Rectangle setHeight(Integer height) {
        return new Rectangle(height, height);
    }
}
