package edu.hw03.Task6;

import org.jetbrains.annotations.NotNull;

public record CStock(String name, int price) implements Comparable<CStock> {

    @Override
    public int compareTo(@NotNull CStock o) {
        return Integer.compare(o.price, this.price);
    }
}
