package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {

    private Rectangle rectangle;

    @SuppressWarnings("MagicNumber")
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(10, 10)),
            Arguments.of(new Square(10))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Обновленный тест из задания")
    @SuppressWarnings("MagicNumber")
    void rectangleArea(Rectangle rect) {
        Rectangle inputRect = rect;

        inputRect = inputRect.setWidth(20);
        inputRect = inputRect.setHeight(10);

        assertThat(inputRect.area()).isEqualTo(200.0);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Обновленный тест из задания без изменения полей")
    @SuppressWarnings("MagicNumber")
    void rectangleAreaWithoutSetters(Rectangle rect) {
        assertThat(rect.area()).isEqualTo(100);
    }

    @Test
    @DisplayName("Квадрат со стороной 5 возвращает площадь равную 25")
    @SuppressWarnings("MagicNumber")
    void testSquare() {
        rectangle = new Square(5);
        assertThat(rectangle.area()).isEqualTo(25);
    }

    @Test
    @DisplayName("Прямоугольник со сторонами 5 и 10 возвращает площадь равную 50")
    @SuppressWarnings("MagicNumber")
    void testRectangle() {
        rectangle = new Rectangle(5, 10);
        assertThat(rectangle.area()).isEqualTo(50);
    }

    @Test
    @DisplayName("Меняем классы")
    @SuppressWarnings("MagicNumber")
    void testChangesClasses() {
        rectangle = new Square(5);
        assertThat(rectangle.area()).isEqualTo(25);
        rectangle = rectangle.setHeight(7);
        rectangle = rectangle.setWidth(8);
        assertThat(rectangle.area()).isEqualTo(56);
        Square square = new Square(7);
        assertThat(square.area()).isEqualTo(49);
        rectangle = square.setSide(3);
        assertThat(rectangle.area()).isEqualTo(9);
        rectangle = rectangle.setHeight(1);
        assertThat(rectangle.area()).isEqualTo(3);
    }

}
