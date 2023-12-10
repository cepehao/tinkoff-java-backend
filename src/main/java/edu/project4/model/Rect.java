package edu.project4.model;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(
    double x,
    double y,
    double width,
    double height
) {
    public boolean contains(Point p) {
        return p.x() >= x && p.x() < width + x && p.y() >= y && p.y() < height + y;
    }

    public Point getRandom() {
        double pointX = ThreadLocalRandom.current().nextDouble(0, width);
        double pointY = ThreadLocalRandom.current().nextDouble(0, height);
        return new Point(pointX, pointY);
    }
}
