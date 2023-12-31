package edu.project4.renderer;

import edu.project4.model.AffineCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class ParallelRenderer extends Renderer {

    private static final int NUMBER_OF_THREADS = 10;
    private final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterPerSample,
        long seed
    ) {
        AffineCoefficients[] coefficientsArray = getRandomAffineCoefficients(samples);

        for (int num = 0; num < samples; num++) {
            executorService.execute(() -> executeIterations(
                canvas,
                world,
                iterPerSample,
                seed,
                transformations,
                coefficientsArray
            ));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return canvas;
    }

    @SneakyThrows
    private void executeIterations(
        FractalImage canvas,
        Rect world,
        int iterPerSample,
        long seed,
        List<Transformation> transformations,
        AffineCoefficients[] coefficientsArray
    ) {
        Point pw = world.getRandom();

        for (int step = START; step < iterPerSample; step++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, coefficientsArray.length);
            var coefficients = coefficientsArray[randomIndex];
            pw = getPointAfterTransformation(coefficients, pw);

            var transformation = transformations.get(ThreadLocalRandom.current().nextInt(0, transformations.size()));
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

                    synchronized (pixel) {
                        setPixelColor(pixel, coefficients);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }
    }
}
