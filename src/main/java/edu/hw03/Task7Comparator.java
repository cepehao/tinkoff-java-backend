package edu.hw03;

import java.util.Comparator;

public class Task7Comparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        if (o1 != null && o2 == null) {
            return 1;
        }

        if (o1 == null && o2 != null) {
            return -1;
        }

        if (o1 == null) {
            return 0;
        }

        return o1.compareTo(o2);
    }
}
