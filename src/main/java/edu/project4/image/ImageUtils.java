package edu.project4.image;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {}

    public static void save(FractalImage image, Path fileName, ImageFormat format) {
        BufferedImage bufferedImage =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }
        try {
            ImageIO.write(bufferedImage, format.name(), fileName.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
