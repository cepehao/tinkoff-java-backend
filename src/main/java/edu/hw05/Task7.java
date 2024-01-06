package edu.hw05;

/*
* Напишите регулярные выражения для строк из алфавита {0, 1} (это важно и нужно использовать при решении):
*/
final class Task7 {
    private static final String MORE_THAN_THREE_SYMBOLS_REGEX = "[01]{2}0[01]*";
    private static final String START_END_WITH_SAME_SYMBOLS_REGEX = "0[01]*0|1[01]*1";
    private static final String LENGTH_BETWEEN_1_AND_30_REGEX = "[01]{1,30}";

    private Task7() {

    }

    /*
    * Cодержит не менее 3 символов, причем третий символ равен 0
    */
    public static boolean task71(String inputString) {
        return inputString.matches(MORE_THAN_THREE_SYMBOLS_REGEX);
    }

    /*
     * Начинается и заканчивается одним и тем же символом
     */
    public static boolean task72(String inputString) {
        return inputString.matches(START_END_WITH_SAME_SYMBOLS_REGEX);
    }

    /*
     * Длина не менее 1 и не более 30
     */
    public static boolean task73(String inputString) {
        return inputString.matches(LENGTH_BETWEEN_1_AND_30_REGEX);
    }
}
