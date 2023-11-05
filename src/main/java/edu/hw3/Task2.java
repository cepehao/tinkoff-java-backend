package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public final class Task2 {

    private Task2() {

    }

    public static String clusterize(String inputString) {
        if (inputString.isEmpty()) {
            throw new IllegalArgumentException("Строка пустая");
        }

        var lvl = 0;

        var sb = new StringBuilder();

        List<String> res = new ArrayList<>();

        for (int i = 0; i < inputString.length(); i++) {
            var curCh = inputString.charAt(i);

            switch (curCh) {
                case '(' -> {
                    lvl++;
                    sb.append(curCh);
                }

                case ')' -> {
                    if (lvl < 1) {
                        throw new IllegalArgumentException("Некорректная строка. Отсутствует открывающая скобка.");
                    }

                    lvl--;
                    sb.append(curCh);
                }

                default -> throw new IllegalArgumentException("Строка должна состоять только из символов ( и )");
            }

            if (lvl == 0) {
                res.add(sb.toString());
                sb.setLength(0);
            }

        }

        if (lvl == 0) {
            return res.toString();
        } else {
            throw new IllegalArgumentException("Не все скобки закрыты");
        }
    }
}
