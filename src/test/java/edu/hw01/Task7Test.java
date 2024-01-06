package edu.hw01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task7Test {

    @BeforeEach
    void setUp() {
        task7 = new Task7();
    }

    private Task7 task7;
    private int number;
    private int shift;
    private int actual;

    @Test
    @DisplayName("Метод должен возвращать 4 в ответ на rotateRight(8, 1)")
    @SuppressWarnings("MagicNumber")
    void giveNumber8Shift1RotateRightShouldReturn4() {
        number = 8;
        shift = 1;

        actual = task7.rotateRight(number, shift);

        Assertions.assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("Метод должен возвращать 1 в ответ на rotateLeft(16, 1)")
    @SuppressWarnings("MagicNumber")
    void giveNumber16Shift1RotateLeftShouldReturn1() {
        number = 16;
        shift = 1;

        actual = task7.rotateLeft(number, shift);

        Assertions.assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("Метод должен возвращать 31 в ответ на rotateLeft(31, 31)")
    @SuppressWarnings("MagicNumber")
    void giveNumber31Shift31RotateLeftShouldReturn31() {
        number = 31;
        shift = 31;

        actual = task7.rotateLeft(number, shift);

        Assertions.assertThat(actual).isEqualTo(31);
    }

    @Test
    @DisplayName("Метод должен возвращать 31 в ответ на rotateRight(31, 1043)")
    @SuppressWarnings("MagicNumber")
    void giveNumber31Shift1043RotateRightShouldReturn31() {
        number = 31;
        shift = 1043;

        actual = task7.rotateRight(number, shift);

        Assertions.assertThat(actual).isEqualTo(31);
    }
}
