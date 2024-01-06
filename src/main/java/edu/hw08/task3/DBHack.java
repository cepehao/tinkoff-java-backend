package edu.hw08.task3;

import java.util.Map;

public abstract class DBHack {
    protected static final int MIN_PASSWORD_LENGTH = 1;
    protected static final int MAX_PASSWORD_LENGTH = 3;
    protected static final String POSSIBLE_SYMBOLS =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    abstract Map<String, String> getData();
}
