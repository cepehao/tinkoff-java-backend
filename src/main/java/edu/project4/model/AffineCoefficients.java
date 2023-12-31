package edu.project4.model;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class AffineCoefficients {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private Color color;

    private static final int COUNT_COLORS = 256;
    private static final double INTERVAL_VALUE = 1.5;

    public static AffineCoefficients create() {
        var a = ThreadLocalRandom.current().nextDouble(-INTERVAL_VALUE, INTERVAL_VALUE);
        var b = ThreadLocalRandom.current().nextDouble(-INTERVAL_VALUE, INTERVAL_VALUE);
        var c = ThreadLocalRandom.current().nextDouble(-INTERVAL_VALUE, INTERVAL_VALUE);
        var d = ThreadLocalRandom.current().nextDouble(-INTERVAL_VALUE, INTERVAL_VALUE);
        var e = ThreadLocalRandom.current().nextDouble(-INTERVAL_VALUE, INTERVAL_VALUE);
        var f = ThreadLocalRandom.current().nextDouble(-INTERVAL_VALUE, INTERVAL_VALUE);

        if (isValid(a, b, d, e)) {
            return new AffineCoefficients(a, b, c, d, e, f, createRandomColor());
        } else {
            return create();
        }
    }

    private static boolean isValid(double a, double b, double d, double e) {
        return (a * a + d * d < 1)
            && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    private static Color createRandomColor() {
        return new Color(
            ThreadLocalRandom.current().nextInt(0, COUNT_COLORS),
            ThreadLocalRandom.current().nextInt(0, COUNT_COLORS),
            ThreadLocalRandom.current().nextInt(0, COUNT_COLORS)
        );
    }
}
