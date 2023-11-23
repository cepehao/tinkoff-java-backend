package edu.hw5;

final class Task4 {
    private static final String SPECIAL_PASS_SYMBOLS_REGEX = ".*[~!@#$%^&*|].*";

    private Task4() {

    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Значение пароля не должно быть null");
        }

//        var pattern = Pattern.compile(SPECIAL_PASS_SYMBOLS_REGEX);
//        var matcher = pattern.matcher(password);
//        return matcher.matches();

        return password.matches(SPECIAL_PASS_SYMBOLS_REGEX);
    }
}
