package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task6Test {

    @BeforeEach
    void setUp() {
        task6 = new Task6();
    }

    private Task6 task6;
    private int number;
    private int actual;

    @Test
    @DisplayName("Метод должен возвращать IllegalArgumentException в ответ на 500")
    @SuppressWarnings("MagicNumber")
    void give500ShouldThrowIllegalArgumentException() {
        number = 500;

        Assertions.assertThatThrownBy(() ->
                task6.countKaprekar(number))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод должен возвращать IllegalArgumentException в ответ на 123456")
    @SuppressWarnings("MagicNumber")
    void give123456ShouldThrowIllegalArgumentException() {
        number = 123456;

        Assertions.assertThatThrownBy(() ->
                task6.countKaprekar(number))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод должен возвращать 0 в ответ на 6174")
    @SuppressWarnings("MagicNumber")
    void give6174ShouldReturn0() {
        number = 6174;

        actual = task6.countKaprekar(number);

        Assertions.assertThat(actual).isEqualTo(0);
    }

    @Test
    @DisplayName("Метод должен возвращать 5 в ответ на 6621")
    @SuppressWarnings("MagicNumber")
    void give6621ShouldReturn5() {
        number = 6621;

        actual = task6.countKaprekar(number);

        Assertions.assertThat(actual).isEqualTo(5);
    }

    @Test
    @DisplayName("Метод должен возвращать 3 в ответ на 1234")
    @SuppressWarnings("MagicNumber")
    void give1234ShouldReturn3() {
        number = 1234;

        actual = task6.countKaprekar(number);

        Assertions.assertThat(actual).isEqualTo(3);
    }
}
