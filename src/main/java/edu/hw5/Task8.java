package edu.hw5;

import java.util.regex.Pattern;

final class Task8 {
    private static final String ODD_LENGTH_REGEX = "([01]{2})*[01]";
    private static final String START_0_ODD_START_1_EVEN_LENGTH_REGEX = "(0([01]{2})*)|(1[01]([01]{2})*)";
    private static final String ZERO_REGEX = "0";
    private static final String NO_FOLLOW_1 = "(?!.*11).*";

    private Task8() {

    }

    /*
     * Нечетной длины
     */
    public static boolean task81(String inputString) {
        return inputString.matches(ODD_LENGTH_REGEX);
    }

    /*
     * Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
     */
    public static boolean task82(String inputString) {
        return inputString.matches(START_0_ODD_START_1_EVEN_LENGTH_REGEX);
    }

    /*
     * Количество 0 кратно 3
     */
    @SuppressWarnings("MagicNumber")
    public static boolean task83(String inputString) {
        var pattern = Pattern.compile(ZERO_REGEX);
        var matcher = pattern.matcher(inputString);

        var count = 0;

        while (matcher.find()) {
            count++;
        }

        return count % 3 == 0;
    }

    /*
     * Нет последовательных 1
     */
    public static boolean task87(String inputString) {
        return inputString.matches(NO_FOLLOW_1);
    }
}
