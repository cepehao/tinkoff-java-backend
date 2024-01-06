package edu.hw01;

import java.util.Arrays;

/**************************************************************************************************************
 * ДЗ 1 задача 6. Постоянная Капрекара.
 * <p>
 * Выберем любое четырёхзначное число n, больше 1000, в котором не все цифры одинаковы.
 * Расположим цифры сначала в порядке возрастания, затем в порядке убывания.
 * Вычтем из большего меньшее. Производя перестановки цифр и вычитания, нули следует сохранять.
 * Описанное действие назовём функцией Капрекара K(n).
 * Повторяя этот процесс с получающимися разностями, не более чем за семь шагов мы получим число 6174,
 * которое будет затем воспроизводить само себя.
 * Это свойство числа 6174 было открыто в 1949 году индийским математиком Д. Р. Капрекаром,
 * в честь которого оно и получило своё название.
 * <p>
 * Пример выполнения K(3524):
 * 5432 – 2345 = 3087
 * 8730 – 0378 = 8352
 * 8532 – 2358 = 6174
 * 7641 – 1467 = 6174
 * <p><
 * Требуется написать рекурсивную функцию, которая для заданного числа будет возвращать количество шагов,
 * которые нужно сделать, чтобы получить 6174.
 * Например, для числа выше ответ будет равен 3.
 * <p>
 * Другие примеры:
 * countK(6621) -> 5
 * countK(6554) -> 4
 * countK(1234) -> 3
 *************************************************************************************************************/

public final class Task6 {
    @SuppressWarnings("MagicNumber")
    private int kaprekar(int number, Integer count) {
        if (number == 6174) {
            return count;
        }

        var stringNumber = String.valueOf(number);

        var masChars = stringNumber.toCharArray();

        Arrays.sort(masChars);

        var sortedStringNumber = new String(masChars);

        var min = Integer.parseInt(sortedStringNumber);
        var max = Integer.parseInt(new StringBuilder(sortedStringNumber).reverse().toString());

        return kaprekar(max - min, count + 1);
    }

    @SuppressWarnings("MagicNumber")
    public int countKaprekar(int number) {
        if (number <= 1000 || number >= 10000) {
            throw new IllegalArgumentException();
        }

        int count = 0;

        return kaprekar(number, count);
    }
}
