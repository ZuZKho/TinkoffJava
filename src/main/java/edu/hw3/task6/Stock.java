package edu.hw3.task6;

import java.util.Objects;

public class Stock {

    private Stock() {
    }

    private int price;
    private String name;

    public Stock(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Stock second)) {
            return false;
        }

        return this.price == second.price && this.name.equals(second.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.price, this.name);
    }
}
