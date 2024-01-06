package edu.hw07.task4;

import java.util.Random;
import java.util.random.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
* Тестирование производилось на восьмиядерном процессоре intel core i5-12450h.
*
* Абсолютная погрешность для 10 млн. итераций:
* Параллельная программа - 0,0001318536
*
* абсолютная погрешность для 100 млн. итераций:
* Параллельная программа - 0,0001827736
*
* Абсолютная погрешность для 1 млрд. итераций:
* Параллельная программа - 0,0000174264
*
* Параллельная программа работает быстрее в среднем в 4 раза. На маленьком количестве симуляций
* последовательная программа показывается лучший результат. Параллельная программа начинает существенно
* обгонять последовательную примерно после 1млн итераций.
*/

public final class CalcPI {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String INCORRECT_INT_MSG = "Введите корректное количество симуляций";

    private static final double COUNT_SIDES = 4;
    private static final double R = 0.5;
    private static final double X0 = 0.5;
    private static final double Y0 = 0.5;

    private CalcPI() {

    }

    public static double lineCalculating(long simulationCount) {
        long circleCount = 0;

        if (simulationCount < 1) {
            throw new IllegalArgumentException(INCORRECT_INT_MSG);
        }

        RandomGenerator randomGenerator = new Random();
        for (long i = 0; i < simulationCount; ++i) {
            double y = randomGenerator.nextDouble();
            double x = randomGenerator.nextDouble();
            if (Math.pow((x - X0), 2) + Math.pow((y - Y0), 2) < Math.pow(R, 2)) {
                ++circleCount;
            }
        }

        return COUNT_SIDES * ((double) circleCount / simulationCount);
    }

    public static double parallelCalculating(long simulationCount, int countThreads) {
        if (simulationCount < 1) {
            throw new IllegalArgumentException(INCORRECT_INT_MSG);
        }

        var simulationsPerThread = simulationCount / countThreads;

        var threads = new PiThread[countThreads];

        for (int i = 0; i < countThreads; i++) {
            threads[i] = new PiThread(simulationsPerThread);
            threads[i].start();
        }

        for (var thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                LOGGER.warn(ex.getStackTrace());
            }
        }

        long circleCount = 0;

        for (var thread : threads) {
            circleCount += thread.getCircleCount();
        }

        return COUNT_SIDES * (double) circleCount / simulationCount;
    }

    private static class PiThread extends Thread {
        private final long simulationsPerThread;
        private long circleCount;

        PiThread(long simulationsPerThread) {
            this.simulationsPerThread = simulationsPerThread;
        }

        @Override
        public void run() {
            Random random = new Random();

            for (int i = 0; i < simulationsPerThread; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();

                if (x * x + y * y <= 1) {
                    circleCount++;
                }
            }
        }

        long getCircleCount() {
            return circleCount;
        }
    }

}
