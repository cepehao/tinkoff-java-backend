package edu.hw8.task2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;

class FixedThreadPoolTest {
    private int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }

    private int expectedFibonacci(int n) {
        int[] fibonacciSequence = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987};
        return fibonacciSequence[n];
    }

    @Test
    public void testFibonacciCalculation() throws InterruptedException {
        var threadPool = FixedThreadPool.create(2);
        var n = 16;

        for (int i = 0; i <= n; i++) {
            var j = i;
            threadPool.execute(() -> {
                var result = calculateFibonacci(j);
                Assertions.assertThat(result).isEqualTo(expectedFibonacci(j));
            });
        }
        threadPool.close();
    }
}
