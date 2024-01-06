package edu.hw06;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task2Test {
    private static final String STRING_RESOURCES_PATH = "src/test/resources/hw6/task2/test.txt";

    @ParameterizedTest
    @ValueSource(strings = {
        "src/test/resources/hw6/task2/test — копия.txt",
        "src/test/resources/hw6/task2/test — копия (2).txt",
        "src/test/resources/hw6/task2/test — копия (3).txt"
    })
    void cloneFile(String copyPath) throws FileNotFoundException {
        Path path = Paths.get(STRING_RESOURCES_PATH);

        if (Files.exists(path)) {
            Task2.cloneFile(path);
            Assertions.assertThat(Files.exists(Paths.get(copyPath))).isTrue();
        } else {
            Assertions.assertThatThrownBy(
                () -> Task2.cloneFile(path)
            ).isInstanceOf(FileNotFoundException.class);
        }
    }
}
