package edu.project4.renderer;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultRenderer extends Renderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long seed
    ) {
        var coefficientsArray = getRandomAffineCoefficients(samples);

        for (int num = 0; num < samples; num++) {
            var pw = world.getRandom();

            for (int step = START; step < iterPerSample; step++) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, coefficientsArray.length);
                var coefficients = coefficientsArray[randomIndex];
                pw = getPointAfterTransformation(coefficients, pw);

                var transformation = variations.get(ThreadLocalRandom.current().nextInt(0, variations.size()));
                pw = transformation.apply(pw);

                if (step >= 0) {
                    double theta = 0;

                    for (int s = 0; s < seed; s++) {
                        theta += 2 * Math.PI / seed;
                        var pwr = getRotatedPoint(pw, theta);

                        if (!world.contains(pwr)) {
                            continue;
                        }

                        var pixel =
                            canvas.getPixel(
                                (int) ((pwr.x() - world.x()) * canvas.getWidth() / world.width()),
                                (int) ((pwr.y() - world.y()) * canvas.getHeight() / world.height())
                            );

                        if (pixel == null) {
                            continue;
                        }

                        setPixelColor(pixel, coefficients);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }

        return canvas;
    }
}
