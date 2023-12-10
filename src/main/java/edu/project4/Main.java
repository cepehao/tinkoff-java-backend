package edu.project4;

import edu.project4.image.GammaCorrection;
import edu.project4.image.ImageProcessor;
import edu.project4.image.ImageUtils;
import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rect;
import edu.project4.renderer.ParallelRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.HeartTransformation;
import edu.project4.transformation.SinusTransformation;
import edu.project4.transformation.SphereTransformation;
import java.nio.file.Path;
import java.util.List;

/*
 * 10 samples, 10000000 iterPerSample:
 * default: 130.7s
 * parallel 5 threads: 25s
 * parallel 10 threads: 15.2s
 *
 * 15 samples, 1000000 iterPerSample:
 * default: 19.2s
 * parallel 5 threads: 4.1s
 * parallel 10 threads: 2.9s
 */

public final class Main {

    private Main() {

    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        var fractalImage = FractalImage.create(1920, 1080);

        Renderer renderer = new ParallelRenderer();

//        long startTime = System.nanoTime();
        fractalImage = renderer.render(
            fractalImage,
            new Rect(-2, -2, 4, 4),
            List.of(new SphereTransformation(), new HeartTransformation(), new SinusTransformation()),
            15,
            1000000,
            7
        );

//        long endTime = System.nanoTime();
//        double elapsedTimeInSeconds = (endTime - startTime) / 1e9;
//        System.out.println("Elapsed Time: " + elapsedTimeInSeconds + " seconds");

        ImageProcessor imageProcess = new GammaCorrection();
        imageProcess.process(fractalImage);

        ImageUtils.save(fractalImage, Path.of("src/main/resources/project4/res.png"), ImageFormat.PNG);
    }
}
