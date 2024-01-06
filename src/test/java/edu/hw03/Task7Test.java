package edu.hw03;

import java.util.TreeMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    void testFromTask() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7Comparator());

        tree.put(null, "test");

        Assertions.assertThat(tree.containsKey(null)).isTrue();
    }
}
