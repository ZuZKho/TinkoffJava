package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    void test1() {
        StockMarket stockMarket = new StockMarket();
        stockMarket.add(new Stock(100, "Tinkoff"));
        stockMarket.add(new Stock(50, "Yandex"));
        stockMarket.add(new Stock(80, "VK"));
        assertEquals(new Stock(100, "Tinkoff"), stockMarket.mostValuableStock());

        stockMarket.remove(new Stock(100, "Tinkoff"));
        assertEquals(new Stock(80, "VK"), stockMarket.mostValuableStock());

        stockMarket.remove(new Stock(50, "Yandex"));
        assertEquals(new Stock(80, "VK"), stockMarket.mostValuableStock());
    }

    @Test
    void test2() {
        StockMarket stockMarket = new StockMarket();
        stockMarket.add(new Stock(1, "Tinkoff"));
        assertEquals(new Stock(1, "Tinkoff"), stockMarket.mostValuableStock());
        stockMarket.add(new Stock(5, "Yandex"));
        assertEquals(new Stock(5, "Yandex"), stockMarket.mostValuableStock());
        stockMarket.add(new Stock(3, "VK"));
        assertEquals(new Stock(5, "Yandex"), stockMarket.mostValuableStock());
    }
}
