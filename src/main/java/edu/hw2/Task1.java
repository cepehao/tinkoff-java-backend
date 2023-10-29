package edu.hw2;

/***********************************************************************************************************************
 * ДЗ 2 задание 1. Калькулятор выражений.
 *
 * Напишите иерархию классов для вычисления математических выражений.
 * public sealed interface Expr {
 *     double evaluate();
 *
 *     public record Constant implements Expr {}
 *     public record Negate implements Expr {}
 *     public record Exponent implements Expr {}
 *     public record Addition implements Expr {}
 *     public record Multiplication implements Expr {}
 * }
 **********************************************************************************************************************/

final class Task1 {

    public sealed interface Expr {
        double evaluate();

        record Constant(double value) implements Expr {
            @Override
            public double evaluate() {
                return value;
            }

            Constant(Expr value) {
                this(value.evaluate());
            }
        }

        record Negate(double value) implements Expr {
            @Override
            public double evaluate() {
                return -value;
            }

            Negate(Expr value) {
                this(value.evaluate());
            }
        }

        record Exponent(double base, double exponent) implements Expr {
            @Override
            public double evaluate() {
                return Math.pow(base, exponent);
            }

            Exponent(Expr base, Expr exponent) {
                this(base.evaluate(), exponent.evaluate());
            }

            Exponent(double base, Expr exponent) {
                this(base, exponent.evaluate());
            }

            Exponent(Expr base, double exponent) {
                this(base.evaluate(), exponent);
            }
        }

        record Addition(double fst, double snd) implements Expr {
            @Override
            public double evaluate() {
                return fst + snd;
            }

            Addition(Expr fst, Expr snd) {
                this(fst.evaluate(), snd.evaluate());
            }

            Addition(double fst, Expr snd) {
                this(fst, snd.evaluate());
            }

            Addition(Expr fst, double snd) {
                this(fst.evaluate(), snd);
            }
        }

        record Multiplication(double fst, double snd) implements Expr {
            @Override
            public double evaluate() {
                return fst * snd;
            }

            Multiplication(Expr fst, Expr snd) {
                this(fst.evaluate(), snd.evaluate());
            }

            Multiplication(double fst, Expr snd) {
                this(fst, snd.evaluate());
            }

            Multiplication(Expr fst, double snd) {
                this(fst.evaluate(), snd);
            }
        }
    }
}
