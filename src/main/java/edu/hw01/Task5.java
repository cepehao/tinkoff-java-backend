package edu.hw01;

/**************************************************************************************************************
 * ДЗ 1 задача 5. Особый палиндром.
 * <p>
 * Будем называть потомком числа новое число, которое создается путем суммирования каждой пары соседних цифр.
 * Например, число 123312 не является палиндромом, но его потомок 363 -- является.
 * Напишите функцию, которая будет возвращать true, если число является палиндромом
 * или если любой из его потомков длиной > 1 (как минимум 2 цифры) является палиндромом.
 * <p>
 * Примеры:
 * <p>
 * isPalindromeDescendant(11211230) -> true // 11211230 -> 2333 -> 56 -> 11
 * isPalindromeDescendant(13001120) -> true // 13001120 -> 4022 ➞ 44
 * isPalindromeDescendant(23336014) -> true // 23336014 -> 5665
 * isPalindromeDescendant(11) -> true
 *************************************************************************************************************/

public final class Task5 {
    private String getChild(String string) {
        var length = string.length();
        StringBuilder child = new StringBuilder();

        for (int i = 0; i < length - 1; i += 2) {
            child.append(
                Integer.parseInt(String.valueOf(string.charAt(i)))
                    + Integer.parseInt(String.valueOf(string.charAt(i + 1)))
            );
        }

        // Если длина строки нечетная - последний символ добавляем в конец
        if (length % 2 == 1) {
            child.append(string.charAt(length - 1));
        }

        return child.toString();
    }

    private boolean isChildPalindrome(String string) {

        var child = getChild(string);

        // Если потомок состоит менее чем из двух символов - возвращаем false
        if (child.length() < 2) {
            return false;
        }

        if (isPalindrome(child)) {
            return true;
        } else {
            return isChildPalindrome(child);
        }
    }

    private boolean isPalindrome(String string) {
        var left = 0;
        var right = string.length() - 1;

        while (left < right) {
            if (string.charAt(left) != string.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    @SuppressWarnings("MagicNumber")
    public boolean isPalindromeDescendant(int number) {
        if (number < 10) {
            return true;
        }

        var stringNumber = String.valueOf(number);

        if (isPalindrome(stringNumber)) {
            return true;
        } else {
            return isChildPalindrome(stringNumber);
        }
    }
}
