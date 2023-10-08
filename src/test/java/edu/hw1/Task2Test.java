package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task2Test {

    @BeforeEach
    void setUp() {
        task2 = new Task2();
    }

    private Task2 task2;
    private int number;
    private int actual;

    @Test
    @DisplayName("Метод должен возвращать 1 в ответ на 0")
    void give0ShouldReturn1() {
        number = 0;

        actual = task2.countDigits(number);

        Assertions.assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("Метод должен возвращать 4 в ответ на 4666")
    @SuppressWarnings("MagicNumber")
    void give4666ShouldReturn4() {
        number = 4666;

        actual = task2.countDigits(number);

        Assertions.assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("Метод должен возвращать 6 в ответ на -123456")
    @SuppressWarnings("MagicNumber")
    void giveMinus123456ShouldReturn6() {
        number = -123456;

        actual = task2.countDigits(number);

        Assertions.assertThat(actual).isEqualTo(6);
    }
}
