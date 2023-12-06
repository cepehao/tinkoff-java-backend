package edu.project3;

import edu.project3.nginx.CConfiguration;
import edu.project3.nginx.CNginxLogParser;
import edu.project3.nginx.CPrinter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Project3Test {
    private static final Path OUTPUT_PATH = Paths.get("src/main/resources/project3/outputFiles/res.md");

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(OUTPUT_PATH);
    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(OUTPUT_PATH);
    }

    @SuppressWarnings("MultipleStringLiterals")
    private static Stream<Arguments> args() {
        return Stream.of(
            Arguments.of((Object) new String[]
                {
                    "--path",
                    "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                    "--format",
                    "md"
               }),
            Arguments.of((Object) new String[]
                {
                    "--path",
                    "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                    "--from",
                    "2016-01-01",
                    "--to",
                    "2019-01-01",
                    "--format",
                    "nan"
                }),
            Arguments.of((Object) new String[]
                {
                    "--path",
                    "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                    "--path",
                    "src/test/resources/project3/input/logs1.txt",
                    "--path",
                    "src/test/resources/project3/input/logs2.txt",
                    "--from",
                    "2015-01-01"
                })
        );
    }

    @ParameterizedTest
    @MethodSource("args")
    void defaultTest(String[] args) {
        var configuration = new CConfiguration(args);
        var parser = new CNginxLogParser(configuration);
        var metrics = parser.parse();
        var printer = new CPrinter(configuration, metrics);
        printer.print();
        Assertions.assertThat(Files.exists(OUTPUT_PATH)).isTrue();
    }

    @Test
    void testNoPathShouldThrowException() {
        Assertions.assertThatThrownBy(
            () -> new CConfiguration(new String[]{
                "--from",
                "2020-11-10",
                "--format",
                "adoc"
            })
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
