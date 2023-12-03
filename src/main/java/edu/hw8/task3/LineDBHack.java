package edu.hw8.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LineDBHack extends DBHack {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Map<String, String> usersDatabase;
    private final Map<String, String> actualPasswords;

    public LineDBHack() {
        this.usersDatabase = readDataBase();
        this.actualPasswords = new HashMap<>();
    }

    @Override
    Map<String, String> getData() {
        for (int i = MIN_PASSWORD_LENGTH; i <= MAX_PASSWORD_LENGTH; i++) {
            generatePasswords("", i);
        }

        return actualPasswords;
    }

    private void generatePasswords(String currentPassword, int length) {
        if (length == 0) {
            var md5Pass = DigestUtils.md5Hex(currentPassword);

            String deleteKey = "";
            if (usersDatabase.containsValue(md5Pass)) {
                for (var entry: usersDatabase.entrySet()) {
                    if (md5Pass.equals(entry.getValue())) {
                        deleteKey = entry.getKey();
                    }
                }

                usersDatabase.remove(deleteKey);

                actualPasswords.put(deleteKey, currentPassword);

                LOGGER.info(deleteKey + ": " + currentPassword + " - " + md5Pass);
            }
        } else {
            for (int i = 0; i < POSSIBLE_SYMBOLS.length() && !usersDatabase.isEmpty(); i++) {
                char currentSymbol = POSSIBLE_SYMBOLS.charAt(i);
                generatePasswords(currentPassword + currentSymbol, length - 1);
            }
        }
    }

    private Map<String, String> readDataBase() {
        var path = Paths.get("src/main/resources/hw8/database.txt");
        Map<String, String> resultMap = new HashMap<>();

        try (var bufferedReader = Files.newBufferedReader(path)) {
            var line = bufferedReader.readLine();
            while (line != null) {
                var splLine = line.split(" ");
                resultMap.put(splLine[0], splLine[1]);
                line = bufferedReader.readLine();
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("Не удалось открыть файл на чтение");
        }
        return resultMap;
    }
}
