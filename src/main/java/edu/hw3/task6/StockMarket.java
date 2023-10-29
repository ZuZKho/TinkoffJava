package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarket implements StockMarketInterface {

    private final PriorityQueue<Stock> priorityQueue = new PriorityQueue<>(new Comparator<Stock>() {
        public int compare(Stock a, Stock b) {
            return b.getPrice() - a.getPrice();
        }
    });

    public void add(Stock stock) {
        priorityQueue.add(stock);
    }

    public void remove(Stock stock) {
        priorityQueue.remove(stock);
    }

    public Stock mostValuableStock() {
        return priorityQueue.peek();
    }

}
