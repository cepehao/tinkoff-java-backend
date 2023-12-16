package edu.hw8.task2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FixedThreadPoolTest {
    private int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    public void testFibonacciCalculation() {
        var threadPool = FixedThreadPool.create(2);
        var n = 16;
        int[] expectedFibonacciSequence = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        int[] testFibonacciSequence = new int[16];

        for (int i = 0; i < n; i++) {
            var j = i;
            threadPool.execute(() -> {
                var curValue = calculateFibonacci(j);
                testFibonacciSequence[j] = curValue;
            });
        }
        threadPool.close();

        Assertions.assertThat(testFibonacciSequence).isEqualTo(expectedFibonacciSequence);
    }
}
