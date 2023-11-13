package edu.hw5;

import java.util.regex.Pattern;

final class Task6 {

    private Task6() {

    }

    public static boolean isSubstring(String substring, String string) {
        if (string == null || substring == null) {
            throw new IllegalArgumentException("Подстрока и строка не должны быть null");
        }

        return string.matches(".*" + Pattern.quote(substring) + ".*");

//        Или лучше так?
//        return string.matches(
//            new StringBuilder()
//                .append(".*")
//                .append(Pattern.quote(substring))
//                .append(".*")
//                .toString()
//        );
    }
}
