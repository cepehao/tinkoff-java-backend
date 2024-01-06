package edu.hw01;

import java.util.regex.Pattern;

/**************************************************************************************************************
 * ДЗ 1 задача 1. Длина видео.
 * <p>
 * Дана строка с длиной видео в формате mm:ss, например 12:44.
 * Напишите функцию, которая возвращает общую длину видео в секундах.
 * <p>
 * Примеры:
 * minutesToSeconds("01:00") -> 60
 * minutesToSeconds("13:56") -> 836
 * minutesToSeconds("10:60") -> -1
 * <p>
 * Замечания:
 * если строка некорректная, например, кол-во секунд больше или равно 60, то нужно вернуть -1
 * количество минут никак не ограничено, например, 999:59 является корректным входным значением
 *************************************************************************************************************/

public final class Task1 {
    private static final long MINUTES_IN_HOUR = 60L;

    private boolean isCorrectTime(String time) {
        var pattern = "^((0\\d)|([1-9]\\d+)):[0-5]\\d$";

        var regex = Pattern.compile(pattern);

        var matcher = regex.matcher(time);

        return matcher.matches();
    }

    public long minutesToSeconds(String time) {
        if (!isCorrectTime(time)) {
            return -1;
        }

        var splitTime = time.split(":");

        var minutes = Integer.parseInt(splitTime[0]);
        var seconds = Integer.parseInt(splitTime[1]);

        return minutes * MINUTES_IN_HOUR + seconds;
    }
}
