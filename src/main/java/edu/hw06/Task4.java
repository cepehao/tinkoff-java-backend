package edu.hw06;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.nio.charset.StandardCharsets.UTF_8;

public final class Task4 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FINAL_MESSAGE = "Programming is learned by writing programs. ― Brian Kernighan";

    private Task4() {

    }

    public static void compositionOutputStreams(Path path) {
        try (
            OutputStream fileOutputStream = Files.newOutputStream(path);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, UTF_8);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)
        ) {
            printWriter.println(FINAL_MESSAGE);
            LOGGER.info("Данные успешно записаны в файл");
        } catch (IOException ex) {
            LOGGER.error("Ошибка во время записи: " + ex.getMessage());
        }
    }

}
