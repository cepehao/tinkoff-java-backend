package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task5Test {

    @BeforeEach
    void setUp() {
        task5 = new Task5();
    }

    private Task5 task5;
    private int number;
    private boolean actual;

    @Test
    @SuppressWarnings("MagicNumber")
    @DisplayName("Метод должен возвращать true в ответ на 11211230 // 11211230 -> 2333 -> 56 -> 11")
    void given11211230ShouldReturnTrue() {
        number = 11211230;

        actual = task5.isPalindromeDescendant(number);

        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    @DisplayName("Метод должен возвращать true в ответ на 5")
    void given5ShouldReturnTrue() {
        number = 5;

        actual = task5.isPalindromeDescendant(number);

        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    @DisplayName("Метод должен возвращать true в ответ на 99")
    void given99ShouldReturnTrue() {
        number = 99;

        actual = task5.isPalindromeDescendant(number);

        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    @DisplayName("Метод должен возвращать true в ответ на 102201")
    void given102201ShouldReturnTrue() {
        number = 102201;

        actual = task5.isPalindromeDescendant(number);

        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    @DisplayName("Метод должен возвращать false в ответ на 2345 // 2345 -> 59 -> 14")
    void given2345ShouldReturnFalse() {
        number = 2345;

        actual = task5.isPalindromeDescendant(number);

        Assertions.assertThat(actual).isEqualTo(false);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    @DisplayName("Метод должен возвращать false в ответ на 11111111 // 11111111 -> 2222 -> 44 -> 8")
    void given11111111ShouldReturnFalse() {
        number = 2345;

        actual = task5.isPalindromeDescendant(number);

        Assertions.assertThat(actual).isEqualTo(false);
    }

    @Test
    @SuppressWarnings("MagicNumber")
    @DisplayName("Метод должен возвращать false в ответ на 21354 // 21354 -> 384 -> 114 -> 24")
    void given21354ShouldReturnFalse() {
        number = 21354;

        actual = task5.isPalindromeDescendant(number);

        Assertions.assertThat(actual).isEqualTo(false);
    }
}
