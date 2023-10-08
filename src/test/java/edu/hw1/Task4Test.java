package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task4Test {

    @BeforeEach
    void setUp() {
        task4 = new Task4();
    }

    private Task4 task4;
    private String string;
    private String actual;

    @Test
    @DisplayName("Метод должен возвращать abcde в ответ на badce")
    void giveBadceShouldReturnAbcde() {
        string = "badce";

        actual = task4.fixString(string);

        Assertions.assertThat(actual).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Метод должен возвращать 'This is a mixed up string.' в ответ на 'hTsii  s aimex dpus rtni.g'")
    void giveCorrectStringReturnCorrectChangedString() {
        string = "hTsii  s aimex dpus rtni.g";

        actual = task4.fixString(string);

        Assertions.assertThat(actual).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Метод должен возвращать пустую строку в ответ на пустую строку")
    void giveEmptyStringShouldReturnEmptyString() {
        string = "";

        actual = task4.fixString(string);

        Assertions.assertThat(actual).isEqualTo("");
    }

    @Test
    @DisplayName("Метод должен возвращать j в ответ на j")
    void giveOneCharacterStringShouldReturnOneCharacterString() {
        string = "j";

        actual = task4.fixString(string);

        Assertions.assertThat(actual).isEqualTo("j");
    }
}
