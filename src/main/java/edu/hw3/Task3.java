package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {

    }

    public static <T> Map<T, Integer> freqDict(List<T> listObjects) {
        var hashMap = new HashMap<T, Integer>();

        for (T obj: listObjects) {
            if (!hashMap.containsKey(obj)) {
                hashMap.put(obj, 1);
            } else {
                hashMap.put(obj, hashMap.get(obj) + 1);
            }
        }

        return hashMap;
    }
}
