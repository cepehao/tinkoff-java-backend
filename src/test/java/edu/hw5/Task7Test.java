package edu.hw5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task7Test {

    @ParameterizedTest
    @ValueSource(strings = {"000", "110", "00000011101010", "110110110", "11000"})
    void giveCorrectStringShouldReturnTrueTask71(String inputString) {
        var actual = Task7.task71(inputString);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", "0", "001", "00100, 11, 111111", "sdf", "as032", "++034"})
    void giveIncorrectStringShouldReturnFalseTask71(String inputString) {
        var actual = Task7.task71(inputString);

        Assertions.assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "101", "000000", "10001", "0001110"})
    void giveCorrectStringShouldReturnTrueTask72(String inputString) {
        var actual = Task7.task72(inputString);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "10", "asd", "000000001", "", "1110000010", "fsdfsgd", "123456"})
    void giveIncorrectStringShouldReturnFalseTask72(String inputString) {
        var actual = Task7.task72(inputString);

        Assertions.assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "10", "01", "10101", "0101010101001", "10000000000000000000000000001"})
    void giveCorrectStringShouldReturnTrueTask73(String inputString) {
        var actual = Task7.task73(inputString);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings =
        {
            "100000000000000000000000000000000000000000000000000000000000000000000",
            "00000000000000000000001010101010101010101010010101011111111000000101010101010010101010101001010101010",
            "11111111111110000000000000000001111111111111100000000000000000111111111100000000000011111111",
            "",
            "4324243",
            "dsdfsdf___)))(("
        }
    )
    void giveIncorrectStringShouldReturnFalseTask73(String inputString) {
        var actual = Task7.task73(inputString);

        Assertions.assertThat(actual).isFalse();
    }
}
