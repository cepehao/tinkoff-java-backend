package edu.hw02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw02.Task1.Expr.Addition;
import static edu.hw02.Task1.Expr.Constant;
import static edu.hw02.Task1.Expr.Exponent;
import static edu.hw02.Task1.Expr.Multiplication;
import static edu.hw02.Task1.Expr.Negate;

class Task1Test {

    private double actual;

    @SuppressWarnings("MagicNumber")
    private double taskTest() {

        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Task1.Expr.Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        return res.evaluate();
    }

    @Test
    @DisplayName("Метод должен возвращать 37.0 в ответ на тест из задания")
    @SuppressWarnings("MagicNumber")
    void giveExampleTestShould37() {

        //given nothing

        actual = taskTest();

        Assertions.assertThat(actual).isEqualTo(37);
    }

    @Test
    @DisplayName("Метод должен возвращать 20")
    @SuppressWarnings("MagicNumber")
    void giveMyTest1Should30() {

        var five = new Constant(5);
        var minusFive = new Constant(new Negate(5));
        var twentyFive = new Exponent(five, 2);
        var thirty = new Addition(twentyFive, minusFive);

        actual = thirty.evaluate();

        Assertions.assertThat(actual).isEqualTo(20);
    }

    @Test
    @DisplayName("Тестирование конструкторов с Expr аргументами. Должен возвращать 32")
    @SuppressWarnings("MagicNumber")
    void testingConstructorsExprArgsShouldReturn32() {

        var two = new Constant(2);
        var four = new Exponent(two, two);
        var sixteen = new Multiplication(four, four);
        var thirtyTwo = new Addition(sixteen, sixteen);

        actual = thirtyTwo.evaluate();

        Assertions.assertThat(actual).isEqualTo(32);
    }

    @Test
    @DisplayName("Тестирование конструкторов с аргументами разных типов. Должен возвращать 60")
    @SuppressWarnings("MagicNumber")
    void testingConstructorsWithDifferentArgsTypesShouldReturn60() {

        var two = new Constant(2);
        var nine = new Exponent(3, two);
        var twelve = new Addition(3, nine);
        var fifteen = new Addition(twelve, 3);
        var thirty = new Multiplication(2, fifteen);
        var sixty = new Multiplication(thirty, 2);

        actual = sixty.evaluate();

        Assertions.assertThat(actual).isEqualTo(60);
    }

    @Test
    @DisplayName("Тестирование с дробными числами")
    @SuppressWarnings("MagicNumber")
    void testingDivisionalNumbers() {

        var constant1 = new Constant(2.5);
        var constant2 = new Constant(3.7);
        var addition = new Addition(constant1, constant2);
        var multiplication = new Multiplication(addition, 0.9);
        var exponent = new Exponent(multiplication, 2.23);

        actual = exponent.evaluate();

        Assertions.assertThat(actual).isEqualTo(46.23749140899503);
    }
}
