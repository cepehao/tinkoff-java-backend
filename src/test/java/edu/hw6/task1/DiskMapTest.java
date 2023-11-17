package edu.hw6.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiskMapTest {
    private static final Path PATH = Paths.get("src/test/resources/hw6/task1/test.txt");

    @BeforeEach
    void setUp() throws IOException {
        if (!Files.exists(PATH)) {
            Files.createFile(PATH);
        }
    }

    @Test
    @SuppressWarnings("MultipleStringLiterals")
    void testAllMethods() {
        var diskMap = new DiskMap(PATH);
        var map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        diskMap.write(map);

        Assertions.assertThat(diskMap.getLastCopy()).isEqualTo(map);

        Assertions.assertThat(diskMap.size()).isEqualTo(map.size());

        Assertions.assertThat(diskMap.containsKey("key2")).isTrue();

        diskMap.remove("key3");

        Assertions.assertThat(diskMap.containsValue("value3")).isFalse();

        diskMap.clear();

        Assertions.assertThat(diskMap.getLastCopy()).isEmpty();

        diskMap.put("newKey", "newValue");

        diskMap.putAll(map);

        map.put("newKey", "newValue");

        Assertions.assertThat(diskMap.getLastCopy()).isEqualTo(map);
    }
}
