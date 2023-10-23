package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarket implements StockMarketInterface {

    private final PriorityQueue<Stock> priorityQueue = new PriorityQueue<>(new Comparator<Stock>() {
        public int compare(Stock a, Stock b) {
            return b.getPrice() - a.getPrice();
        }
    });

    /** Добавить акцию */
    public void add(Stock stock) {
        priorityQueue.add(stock);
    }

    /** Удалить акцию */
    public void remove(Stock stock) {
        priorityQueue.remove(stock);
    }

    /** Самая дорогая акция */
    public Stock mostValuableStock() {
        return priorityQueue.peek();
    }

}
