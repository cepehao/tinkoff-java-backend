package edu.project4.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;
}
