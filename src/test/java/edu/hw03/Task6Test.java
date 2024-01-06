package edu.hw03;

import edu.hw03.Task6.CStock;
import edu.hw03.Task6.CStockMarket;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class Task6Test {
    private static Stream<Arguments> arguments() {
        var fst = new CStock("yandex", 100);
        var snd = new CStock("tinkoff", 300);
        var third = new CStock("amazon", 500);
        var fourth = new CStock("ozon", 200);
        return Stream.of(
            Arguments.of(List.of(fst, snd, third, fourth)),
            Arguments.of(List.of(fourth, fst, third, snd)),
            Arguments.of(List.of(snd, fst, fourth, third))
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    @DisplayName("Тестирование корректных данных")
    void testStockMarketImplementation(List<CStock> stocks) {
        var stockMarket = new CStockMarket();

        for (var stock : stocks) {
            stockMarket.add(stock);
        }

        Assertions.assertThat(stockMarket.mostValuableStock().price()).isEqualTo(500);
        stockMarket.remove(new CStock("amazon", 500));

        Assertions.assertThat(stockMarket.mostValuableStock().price()).isEqualTo(300);
        stockMarket.remove(new CStock("ozon", 200));

        Assertions.assertThat(stockMarket.mostValuableStock().price()).isEqualTo(300);
        stockMarket.remove(new CStock("tinkoff", 300));

        Assertions.assertThat(stockMarket.mostValuableStock().price()).isEqualTo(100);
        stockMarket.remove(new CStock("yandex", 100));

        Assertions.assertThat(stockMarket.mostValuableStock()).isNull();
    }
}
