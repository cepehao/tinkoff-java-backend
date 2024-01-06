package edu.hw01;

/**************************************************************************************************************
 * ДЗ 1 задача 7. Циклический битовый сдвиг.
 * <p>
 * В Java есть базовые битовые операции, но нет циклического сдвига битов.
 * Напишите 2 функции:
 * int rotateLeft(int n, int shift)
 * int rotateRight(int n, int shift)
 * где
 * n -- целое число положительное число
 * shift -- размер циклического сдвига
 * <p>
 * Примеры:
 * rotateRight(8, 1) -> 4 // 1000 -> 0100
 * rotateLeft(16, 1) -> 1 // 10000 -> 00001
 * rotateLeft(17, 2) -> 6 // 10001 -> 00110
 *************************************************************************************************************/

public final class Task7 {
    public int rotateLeft(int number, int shift) {
        var sb = new StringBuilder(Integer.toBinaryString(number));

        for (int i = 0; i < shift; i++) {
            var first = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(first);
        }

        return Integer.parseInt(sb.toString(), 2);
    }

    public int rotateRight(int number, int shift) {
        var sb = new StringBuilder(Integer.toBinaryString(number));

        for (int i = 0; i < shift; i++) {
            var lastIndex = sb.length() - 1;
            var last = sb.charAt(lastIndex);
            sb.deleteCharAt(lastIndex);
            sb.insert(0, last);
        }

        return Integer.parseInt(sb.toString(), 2);
    }
}
