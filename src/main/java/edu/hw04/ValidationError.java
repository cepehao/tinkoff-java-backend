package edu.hw04;

public record ValidationError(
    String message,
    Field field
) {
    enum Field {
        NAME, TYPE, SEX, AGE, HEIGHT, WEIGHT, BITES
    }

    @Override
    public String toString() {
        return "ValidationError{"
            + "field=" + field
            + ", errorMessage='" + message + "'"
            + "}";
    }
}
