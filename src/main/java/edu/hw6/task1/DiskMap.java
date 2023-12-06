package edu.hw6.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DiskMap implements Map<String, String> {
    private static final String SEPARATOR = ":";
    private static final Logger LOGGER = LogManager.getLogger();

    private Map<String, String> lastCopy;
    private Path path;

    public DiskMap(Path path) {
        this.path = path;
        read();
        LOGGER.info("Копия успешно сохранена");
    }

    public void read() {
        lastCopy = new HashMap<>();

        try (var reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(":");
                if (values.length == 2) {
                    lastCopy.put(values[0], values[1]);
                } else {
                    throw new IOException("Формат строки не соответствует формату \"ключ:значение\"");
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void write(@NotNull Map<? extends String, ? extends String> newMap) {
        lastCopy = new HashMap<>();

        try (var writer = Files.newBufferedWriter(path)) {
            for (var entry: newMap.entrySet()) {
                var key = entry.getKey();
                var value = entry.getValue();

                writer.write(key + ":" + value);
                writer.newLine();

                lastCopy.put(key, value);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Map<String, String> getLastCopy() {
        return lastCopy;
    }

    @Override
    public int size() {
        return lastCopy.size();
    }

    @Override
    public boolean isEmpty() {
        return lastCopy.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return lastCopy.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return lastCopy.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return lastCopy.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        lastCopy.put(key, value);
        write(lastCopy);
        return lastCopy.get(key);
    }

    @Override
    public String remove(Object key) {
        return lastCopy.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry: m.entrySet()) {
            lastCopy.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        write(new HashMap<>());
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return lastCopy.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return lastCopy.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return lastCopy.entrySet();
    }
}
