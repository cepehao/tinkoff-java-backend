package edu.project1.dictionary;

import java.util.Random;

public final class CDictionary {
    private static final Random RANDOM = new Random();

    private final String[] words;

    public CDictionary(String[] dictionary) {
        this.words = dictionary;
    }

    public String randomWord() {
        return words[RANDOM.nextInt(words.length)];
    }
}

