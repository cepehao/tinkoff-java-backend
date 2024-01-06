package edu.hw05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task8Test {

    @ParameterizedTest
    @ValueSource(
        strings = {
            "1", "111", "010", "101", "11100", "1010010", "1111110", "00001"
        }
    )
    void giveCorrectStringShouldReturnTrueTask81(String inputString) {
        var actual = Task8.task81(inputString);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "", "00", "11", "01", "10", "0101", "1010", "111111", "000000", "00110011", "1001100110101011101001010101",
            "dasfsf", "hellow", "(**&&*(#_@#)", "sdfsSDFerw", "00aaBB", "kfk11fsld00"
        }
    )
    void giveIncorrectStringShouldReturnFalseTask81(String inputString) {
        var actual = Task8.task81(inputString);

        Assertions.assertThat(actual).isFalse();
    }


    @ParameterizedTest
    @ValueSource(
        strings = {
            "0", "001", "011", "01101", "0000000", "010101011", "00110011011",
            "11", "1111", "1010", "1111111100", "1000000111", "11001100110011"
        }
    )
    void giveCorrectStringShouldReturnTrueTask82(String inputString) {
        var actual = Task8.task82(inputString);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "00", "0111", "0101", "0000001111",
            "1", "111", "101", "10011", "110011010011101",
            "asfssdf", "12345", "11aa11", "_)("
        }
    )
    void giveIncorrectStringShouldReturnFalseTask82(String inputString) {
        var actual = Task8.task82(inputString);

        Assertions.assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "000", "010101", "000111", "111000", "001001000100", "111000111000000000",
            "011001110010001000100100", "000000", "111111111111111000000000000000", ""
        }
    )
    void giveCorrectStringShouldReturnTrueTask83(String inputString) {
        var actual = Task8.task83(inputString);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "00", "110", "11101110", "001100", "0000000", "11100011100", "0101010101"
        }
    )
    void giveIncorrectStringShouldReturnFalseTask83(String inputString) {
        var actual = Task8.task83(inputString);

        Assertions.assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "0101010101010101010", "", "000000000", "01000010001001", "00100", "00000100001"
        }
    )
    void giveCorrectStringShouldReturnTrueTask87(String inputString) {
        var actual = Task8.task87(inputString);

        Assertions.assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "11", "111", "00111100", "0110110001111", "11111111", "011011", "010101011"
        }
    )
    void giveIncorrectStringShouldReturnFalseTask87(String inputString) {
        var actual = Task8.task87(inputString);

        Assertions.assertThat(actual).isFalse();
    }

}
