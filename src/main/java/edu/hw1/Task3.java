package edu.hw1;

/**************************************************************************************************************
 * ДЗ 1 задача 3. Вложенный массив.
 * <p>
 * Напишите функцию, которая возвращает true, если первый
 * массив может быть вложен во второй, и false в противном случае.
 * <p>
 * Массив может быть вложен, если:
 * 1) min(a1) больше чем min(a2)
 * 2) max(a1) меньше, чем max(a2)
 * <p>
 * Примеры:
 * isNestable([1, 2, 3, 4], [0, 6]) -> true
 * isNestable([3, 1], [4, 0]) -> true
 * isNestable([9, 9, 8], [8, 9]) -> false
 * isNestable([1, 2, 3, 4], [2, 3]) -> false
 *************************************************************************************************************/

public final class Task3 {
    private int findMin(int[] mas) {
        var min = Integer.MAX_VALUE;

        for (int elem : mas) {
            if (elem < min) {
                min = elem;
            }
        }
        return min;
    }

    private int findMax(int[] mas) {
        var max = Integer.MIN_VALUE;

        for (int elem : mas) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    public boolean isNestable(int[] a1, int[] a2) {
        var minA1 = findMin(a1);
        var minA2 = findMin(a2);
        var maxA1 = findMax(a1);
        var maxA2 = findMax(a2);

        return minA1 > minA2 && maxA1 < maxA2;
    }
}
