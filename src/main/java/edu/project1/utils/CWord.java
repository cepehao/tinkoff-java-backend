package edu.project1.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CWord {
    private final String word;
    private final List<Character> state;
    private int countOpen = 0;
    private final Set<Character> playerAttempts = new HashSet<>();

    public CWord(String word) {
        this.word = word;
        this.state = new ArrayList<>(Collections.nCopies(word.length(), '*'));
    }

    @Override
    public String toString() {
        return word;
    }

    public int getLength() {
        return word.length();
    }

    public String curState() {
        var sb = new StringBuilder();

        for (var ch : state) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public boolean alreadyInput(String ch) {
        return playerAttempts.contains(ch.charAt(0));
    }

    public boolean isSubstring(char ch) {
        var isFind = false;

        for (int i = 0; i < word.length(); i++) {
            if (ch == word.charAt(i)) {
                state.set(i, ch);
                playerAttempts.add(ch);
                countOpen++;
                isFind = true;
            }
        }

        return isFind;
    }

    public boolean allOpen() {
        return countOpen == word.length();
    }
}
