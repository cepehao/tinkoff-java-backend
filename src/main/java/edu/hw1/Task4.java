package edu.hw1;

/**************************************************************************************************************
 * ДЗ 1 задача 4. Сломанная строка.
 * <p>
 * Все мои строки перепутались и каждая пара символов поменялась местами.
 * Напишите функцию, которая исправляет такие строки и возвращает правильный порядок.
 * <p>
 * Примеры:
 * fixString("123456") ➞ "214365"
 * fixString("hTsii  s aimex dpus rtni.g") ➞ "This is a mixed up string."
 * fixString("badce") ➞ "abcde"
 *************************************************************************************************************/

public final class Task4 {
    public String fixString(String string) {
        if (string.length() < 2) {
            return string;
        }

        var resultString = new StringBuilder();
        var length = string.length();

        for (int i = 0; i < length - 1; i += 2) {
            resultString.append(string.charAt(i + 1));
            resultString.append(string.charAt(i));
        }

        if (length % 2 == 1) {
            resultString.append(string.charAt(length - 1));
        }

        return resultString.toString();
    }
}
