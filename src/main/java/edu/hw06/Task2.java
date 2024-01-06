package edu.hw06;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COPY_POSTFIX = " — копия";

    private Task2() {

    }

    public static void cloneFile(Path path) throws FileNotFoundException {

        if (!Files.exists(path)) {
            LOGGER.error("Файл не найден");
            throw new FileNotFoundException();
        }

        var originalFile = path.toFile();
        var fileName = originalFile.getName();
        var baseName = fileName.substring(0, fileName.lastIndexOf("."));
        var extension = fileName.substring(fileName.lastIndexOf("."));

        var copyIndex = 1;

        var copyFileName = fileName;

        while (Files.exists(path.resolveSibling(copyFileName))) {
             if (copyIndex == 1) {
                copyFileName = baseName + COPY_POSTFIX + extension;
             } else {
                 copyFileName = baseName
                     + COPY_POSTFIX
                     + String.format(" (%d)", copyIndex)
                     + extension;
             }

             copyIndex++;
        }

        try {
            Files.copy(path, path.resolveSibling(copyFileName));
            LOGGER.info("Файл успешно скопирован");
        } catch (IOException ex) {
            LOGGER.warn("Файл не был скопирован: " + ex.getMessage());
        }
    }

}
