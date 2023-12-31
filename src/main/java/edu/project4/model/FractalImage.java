package edu.project4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class FractalImage {
    private final Pixel[][] data;
    @Getter
    private final int width;
    @Getter
    private final int height;

    public static FractalImage create(int width, int height) {
        var data = new Pixel[height][width];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = new Pixel();
            }
        }
        return new FractalImage(data, width, height);
    }

    public Pixel getPixel(int x, int y) {
        return data[y][x];
    }
}
