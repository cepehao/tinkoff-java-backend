package edu.hw8.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {

    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        var x = new ParallelDBHack();

        long startTime = System.nanoTime();
        x.getData();
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        double elapsedTimeInMilliseconds = (double) elapsedTime / 1_000_000;
        LOGGER.info("Время выполнения: " + elapsedTimeInMilliseconds + " миллисекунд");
    }
}
