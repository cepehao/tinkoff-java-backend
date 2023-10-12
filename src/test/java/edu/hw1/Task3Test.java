package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task3Test {

    @BeforeEach
    void setUp() {
        task3 = new Task3();

    }

    private Task3 task3;
    private int[] a1;
    private int[] a2;
    private boolean actual;

    @Test
    @DisplayName("Метод должен возвращать true в ответ на [1, 2, 3, 4], [0, 6]")
    @SuppressWarnings("MagicNumber")
    void giveCorrectArraysShouldReturnTrue1() {
        a1 = new int[] {1, 2, 3, 4};
        a2 = new int[] {0, 6};

        actual = task3.isNestable(a1, a2);

        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("Метод должен возвращать true в ответ на [3, 1], [4, 0]")
    @SuppressWarnings("MagicNumber")
    void giveCorrectArraysShouldReturnTrue2() {
        a1 = new int[] {3, 1};
        a2 = new int[] {4, 0};

        actual = task3.isNestable(a1, a2);

        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("Метод должен возвращать true в ответ на [9, 9, 8], [8, 9]")
    @SuppressWarnings("MagicNumber")
    void giveIncorrectArraysShouldReturnFalse1() {
        a1 = new int[] {9, 9, 8};
        a2 = new int[] {8, 9};

        actual = task3.isNestable(a1, a2);

        Assertions.assertThat(actual).isEqualTo(false);
    }

    @Test
    @DisplayName("Метод должен возвращать true в ответ на [120, -5, 30, 70], [-5, 10, -3, 20, 9, 100]")
    @SuppressWarnings("MagicNumber")
    void giveIncorrectArraysShouldReturnFalse2() {
        a1 = new int[] {120, -5, 30, 70};
        a2 = new int[] {-5, 10, -3, 20, 9, 100};

        actual = task3.isNestable(a1, a2);

        Assertions.assertThat(actual).isEqualTo(false);
    }

    @Test
    @DisplayName("Метод должен возвращать false в ответ на [1, 10, 5, 40], [0]")
    @SuppressWarnings("MagicNumber")
    void giveCorrectArraysShouldReturnFalse3() {
        a1 = new int[] {1, 10, 5, 40};
        a2 = new int[] {0};

        actual = task3.isNestable(a1, a2);

        Assertions.assertThat(actual).isEqualTo(false);
    }
}
