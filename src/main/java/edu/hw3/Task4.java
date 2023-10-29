package edu.hw3;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Task4 {
    private Task4() {

    }

    @SuppressWarnings("MagicNumber")
    public static String convertToRoman(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("Введите число от 1 до 3999");
        }

        Map<String, Integer> romanToArabic = new LinkedHashMap<>();

        romanToArabic.put("M", 1000);
        romanToArabic.put("CM", 900);
        romanToArabic.put("D", 500);
        romanToArabic.put("CD", 400);
        romanToArabic.put("C", 100);
        romanToArabic.put("XC", 90);
        romanToArabic.put("L", 50);
        romanToArabic.put("XL", 40);
        romanToArabic.put("X", 10);
        romanToArabic.put("IX", 9);
        romanToArabic.put("V", 5);
        romanToArabic.put("IV", 4);
        romanToArabic.put("I", 1);

        StringBuilder roman = new StringBuilder();
        var curNum = num;

        for (Map.Entry<String, Integer> entry : romanToArabic.entrySet()) {
            String romanNumeral = entry.getKey();
            var value = entry.getValue();

            while (curNum >= value) {
                roman.append(romanNumeral);
                curNum -= value;
            }
        }

        return roman.toString();
    }
}
