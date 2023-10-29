package edu.hw3;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task1Test {

    private static Stream<Arguments> arguments() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of(
                "Any fool can write code that a computer can understand. Good programmers write "
                    + "code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg "
                    + "sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
            ),
            Arguments.of(
                "English texts for beginners to practice reading and comprehension online and for free. "
                    + "Practicing your comprehension of written English will both improve your vocabulary and "
                    + "understanding of grammar and word order. The texts below are designed to help you develop while "
                    + "giving you an instant evaluation of your progress.",
                "Vmtorhs gvcgh uli yvtrmmvih gl kizxgrxv ivzwrmt zmw xlnkivsvmhrlm lmormv zmw uli uivv. "
                    + "Kizxgrxrmt blfi xlnkivsvmhrlm lu dirggvm Vmtorhs droo ylgs rnkilev blfi elxzyfozib zmw "
                    + "fmwvihgzmwrmt lu tiznnzi zmw dliw liwvi. Gsv gvcgh yvold ziv wvhrtmvw gl svok blf wvevolk "
                    + "dsrov trermt blf zm rmhgzmg vezofzgrlm lu blfi kiltivhh."
            ),
            Arguments.of("a!@#$%^&   &*()_-+=//,.", "z!@#$%^&   &*()_-+=//,."),
            Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource({"arguments"})
    @DisplayName("Тестирование набора строк")
    void atbash(String input, String expected) {
        Assertions.assertThat(Task1.atbash(input)).isEqualTo(expected);
    }
}
