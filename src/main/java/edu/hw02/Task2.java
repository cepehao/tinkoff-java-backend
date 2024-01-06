package edu.hw02;

/***********************************************************************************************************************
 * ДЗ 2 задание 2. Квадрат и прямоугольник.
 *
 * Допустим, у вас есть классы Rectangle и Square с методами setWidth, setHeight и area:
 * public class Rectangle {
 *     private int width;
 *     private int height;
 *
 *     void setWidth(int width) {
 *         this.width = width;
 *     }
 *
 *     void setHeight(int height) {
 *         this.height = height;
 *     }
 *
 *     double area() {
 *         return width * height;
 *     }
 * }
 *
 * public class Square extends Rectangle {
 *     @Override
 *     void setWidth(int width) {
 *         super.setHeight(width);
 *         super.setWidth(width);
 *     }
 *
 *     @Override
 *     void setHeight(int height) {
 *         super.setHeight(height);
 *         super.setWidth(height);
 *     }
 * }
 *
 * Задание:
 * Найдите решение проблемы, удовлетворяющее следующим ограничениям:
 * - Не нарушается математическая логика, то есть квадрат является прямоугольником с точки зрения типизации
 * - При этом не нарушается принцип подстановки
 * - Все так же присутствует понятие высоты и ширины, а также операция вычисления площади
 * - Реализация класса Rectangle не должна предполагать существование класса Square,
 *   иными словами, не нарушен принцип открытости-закрытости
 **********************************************************************************************************************/

final class Task2 {
    public static class Rectangle {
        private final int width;
        private final int height;

        Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public Rectangle setWidth(int width) {
            return new Rectangle(width, height);
        }

        public Rectangle setHeight(int height) {
            return new Rectangle(width, height);
        }

        public double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        Square(int side) {
            super(side, side);
        }

        public Square setSide(int side) {
            return new Square(side);
        }
    }
}
