package edu.hw1;

/**************************************************************************************************************
 * ДЗ 1 задача 2. Количество цифр.
 * <p>
 * Напишите функцию, которая возвращает количество цифр в десятичной форме числа.
 * Пользоваться преобразованием в строку запрещено.
 * <p>
 * Примеры:
 * countDigits(4666) -> 4
 * countDigits(544) -> 3
 * countDigits(0) -> 1
 *************************************************************************************************************/
public final class Task2 {
    private static final int DIVIDER = 10;

    public int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        var count = 0;
        var curNumber = Math.abs(number);

        while (curNumber > 0) {
            curNumber /= DIVIDER;
            count++;
        }

        return count;
    }
}
