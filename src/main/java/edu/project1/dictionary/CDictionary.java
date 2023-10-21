package edu.project1.dictionary;

import java.util.Random;

public final class CDictionary implements IDictionary {
    private static final Random RANDOM = new Random();

    private final String[] dictionary;

    public CDictionary(String[] dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String randomWord() {
        return dictionary[RANDOM.nextInt(dictionary.length)];
    }
}

