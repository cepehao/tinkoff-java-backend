package edu.project2.model;

public enum CCell {
    WALL,
    PASSAGE,
    PATH;

    @Override
    public String toString() {
        return switch (this) {
            case WALL -> "x";
            case PASSAGE -> "+";
            case PATH -> "\u001B[31m+\u001B[0m";
        };
    }

    public boolean isPassage() {
        return this == PASSAGE;
    }
}
