package edu.hw5;

final class Task5 {
    // Для нанесения на табличку утверждены только те буквы кириллицы, которые имеют аналоги в латинском алфавите.
    // В номерных знаках Российской Федерации используют 12 букв алфавита — это А, В, Е, К, М, Н, О, Р, С, Т, У, Х.
    private static final String STATE_NUMBER_REGEX
        = "[АВЕКМНОРСТУХ](?!000)\\d{3}[АВЕКМНОРСТУХ]{2}(?!(00\\d?|0[1-9]{2}))\\d{2,3}";

    private Task5() {

    }

    public static boolean isValidStateNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("Значение номера не должно быть null");
        }

        return number.matches(STATE_NUMBER_REGEX);
    }
}
