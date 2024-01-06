package edu.hw03;

public final class Task1 {

    private static final int LOWER_CASE_A_NUMBER = 97;
    private static final int LOWER_CASE_Z_NUMBER = 127;
    private static final int UPPER_CASE_Z_NUMBER = 90;
    private static final int UPPER_CASE_A_NUMBER = 65;

    private Task1() {

    }

    public static String atbash(String str) {
        var sb = new StringBuilder(str);

        for (int i = 0; i < sb.length(); i++) {
            var ch = sb.charAt(i);

            if (ch >= UPPER_CASE_A_NUMBER && ch <= UPPER_CASE_Z_NUMBER
                || ch >= LOWER_CASE_A_NUMBER && ch <= LOWER_CASE_Z_NUMBER) {

                char fstChar;
                char lastChar;

                if (Character.isUpperCase(ch)) {
                    fstChar = 'A';
                    lastChar = 'Z';
                } else {
                    fstChar = 'a';
                    lastChar = 'z';
                }

                var newCh = (char) (lastChar - ch + fstChar);

                sb.setCharAt(i, newCh);
            }
        }

        return sb.toString();
    }
}
