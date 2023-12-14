package edu.hw7.task2;

import java.util.stream.LongStream;

public final class Factorial {

    private Factorial() {

    }

    public static long calculateFactorial(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Введите положительное число >= 1");
        }

        return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(1, (a, b) -> a * b);
    }
}
