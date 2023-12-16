package edu.hw8.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParallelDBHack extends DBHack {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConcurrentMap<String, String> usersDatabase;
    private final ConcurrentMap<String, String> actualPasswords;
    private final ExecutorService executor;
    private final CountDownLatch latch;

    public ParallelDBHack() {
        this.usersDatabase = new ConcurrentHashMap<>(readDataBase());
        this.actualPasswords = new ConcurrentHashMap<>();
        this.executor = Executors.newCachedThreadPool();
        this.latch = new CountDownLatch(usersDatabase.size());
    }

    @Override
    Map<String, String> getData() {
        for (int i = MIN_PASSWORD_LENGTH; i <= MAX_PASSWORD_LENGTH; i++) {
            generatePasswords("", i);
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
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
                latch.countDown();

                actualPasswords.put(deleteKey, currentPassword);

                LOGGER.info(deleteKey + ": " + currentPassword + " - " + md5Pass);
            }
        } else {
            for (int i = 0; i < POSSIBLE_SYMBOLS.length() && !usersDatabase.isEmpty(); i++) {
                char currentSymbol = POSSIBLE_SYMBOLS.charAt(i);
                executor.execute(() -> generatePasswords(currentPassword + currentSymbol, length - 1));
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
