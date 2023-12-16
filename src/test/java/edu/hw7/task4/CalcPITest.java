package edu.hw7.task4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CalcPITest {

    private static final long MILLION = 1_000_000L;
    private static final int COUNT_THREADS = 1;
    private static final String STRING_FORMAT = "%.2f";

    @Test
    void testResultPiLine() {
        Assertions.assertThat(
            String.format(STRING_FORMAT, CalcPI.lineCalculating(MILLION))
        ).isEqualTo(String.format(STRING_FORMAT, Math.PI));
    }

    @Test
    void testResultPiParallel() {
        Assertions.assertThat(
            String.format(STRING_FORMAT, CalcPI.parallelCalculating(MILLION, COUNT_THREADS))
        ).isEqualTo(String.format(STRING_FORMAT, Math.PI));
    }

    @Test
    void incorrectData() {
        Assertions.assertThatThrownBy(
            () -> CalcPI.lineCalculating(0)
        ).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(
            () -> CalcPI.parallelCalculating(-MILLION, COUNT_THREADS)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
