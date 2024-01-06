
package edu.hw07.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Counter {
    public final static Logger LOGGER = LogManager.getLogger();

    private int counter;

    private synchronized void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void work(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Укажите положительное число");
        }

        var incrementor1 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                increment();
            }
        });

        var incrementor2 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                increment();
            }
        });

        incrementor1.start();
        incrementor2.start();

        try {
            incrementor1.join();
            incrementor2.join();
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
