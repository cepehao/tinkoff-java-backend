package edu.hw3;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task8Test {

    @Test
    @DisplayName("Тест набора данных из примера")
    @SuppressWarnings("MagicNumber")
    void testWithList() {
        Task8BackwardIterator<Integer> iterator = new Task8BackwardIterator<>(List.of(1, 2, 3));
        Assertions.assertThat(iterator.next()).isEqualTo(3);
        Assertions.assertThat(iterator.next()).isEqualTo(2);
        Assertions.assertThat(iterator.next()).isEqualTo(1);
        Assertions.assertThat(iterator.hasNext()).isFalse();
    }
}
