package edu.hw3.Task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class CStockMarket implements IStockMarket {
    private final Queue<CStock> stockQueue = new PriorityQueue<>();

    @Override
    public void add(CStock stock) {
        stockQueue.add(stock);
    }

    @Override
    public void remove(CStock stock) {
        stockQueue.remove(stock);
    }

    @Override
    public CStock mostValuableStock() {
        return stockQueue.peek();
    }
}
