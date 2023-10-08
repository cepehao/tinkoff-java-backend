package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task1Test {

    @BeforeEach
    void setUp() {
        task1 = new Task1();
    }

    private Task1 task1;
    private String time;
    private long actual;

    @Test
    @DisplayName("Метод должен возвращать 0 в ответ на 00:00")
    void give0Minutes0SecondsShouldReturn0() {
        time = "00:00";

        actual = task1.minutesToSeconds(time);

        Assertions.assertThat(actual).isEqualTo(0L);
    }

    @Test
    @DisplayName("Метод должен возвращать 836 в ответ на 13:56")
    @SuppressWarnings("MagicNumber")
    void give13Minutes56SecondsShouldReturn836() {
        time = "13:56";

        actual = task1.minutesToSeconds(time);

        Assertions.assertThat(actual).isEqualTo(836L);
    }

    @Test
    @DisplayName("Метод должен возвращать 5954560 в ответ на 99242:40")
    @SuppressWarnings("MagicNumber")
    void give99242Minutes40SecondsShouldReturn5954560() {
        time = "99242:40";

        actual = task1.minutesToSeconds(time);

        Assertions.assertThat(actual).isEqualTo(5954560L);
    }

    @Test
    @DisplayName("Метод должен возвращать -1 в ответ на 10:61")
    void give10Minutes61SecondsShouldReturnMinusOne() {
        time = "10:61";

        actual = task1.minutesToSeconds(time);

        Assertions.assertThat(actual).isEqualTo(-1L);
    }
}
