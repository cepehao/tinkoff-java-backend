package edu.hw06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Task4Test {
    private static final String ACTUAL_PATH = "src/test/resources/hw6/task4/streams.txt";
    private static final String CORRECT_PATH = "src/test/resources/hw6/task4/streamsCorrect.txt";

    @Test
    void compositionOutputStreams() throws IOException {
        Path pathActual = Paths.get(ACTUAL_PATH);

        if (!Files.exists(pathActual)) {
            try {
                Files.createFile(pathActual);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Task4.compositionOutputStreams(pathActual);

        Assertions.assertThat(Files.readAllLines(pathActual))
            .isEqualTo(Files.readAllLines(Paths.get(CORRECT_PATH)));
    }
}
