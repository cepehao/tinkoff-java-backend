package edu.project4.renderer;

import edu.project4.model.AffineCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;

public abstract class Renderer {
    protected static final int START = -20;

    public abstract FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long seed
    );

    protected AffineCoefficients[] getRandomAffineCoefficients(int samples) {
        AffineCoefficients[] transformations = new AffineCoefficients[samples];
        for (int i = 0; i < samples; i++) {
            transformations[i] = AffineCoefficients.create();
        }
        return transformations;
    }

    protected Point getPointAfterTransformation(AffineCoefficients coefficients, Point pw) {
        double x = getNewXWithCoefficients(coefficients, pw);
        double y = getNewYWithCoefficients(coefficients, pw);
        return new Point(x, y);
    }

    protected Point getRotatedPoint(Point pw, double theta) {
        double xRot = pw.x() * Math.cos(theta) - pw.y() * Math.sin(theta);
        double yRot = pw.x() * Math.sin(theta) + pw.y() * Math.cos(theta);
        return new Point(xRot, yRot);
    }

    protected void setPixelColor(Pixel pixel, AffineCoefficients coefficients) {
        if (pixel.getHitCount() == 0) {
            pixel.setR(coefficients.getColor().getRed());
            pixel.setG(coefficients.getColor().getGreen());
            pixel.setB(coefficients.getColor().getBlue());
        } else {
            pixel.setR((pixel.getR() + coefficients.getColor().getRed()) / 2);
            pixel.setG((pixel.getG() + coefficients.getColor().getGreen()) / 2);
            pixel.setB((pixel.getB() + coefficients.getColor().getBlue()) / 2);
        }
    }

    private double getNewXWithCoefficients(AffineCoefficients coefficients, Point pw) {
        return coefficients.getA() * pw.x() + coefficients.getB() * pw.y()
            + coefficients.getC();
    }

    private double getNewYWithCoefficients(AffineCoefficients coefficients, Point pw) {
        return coefficients.getD() * pw.x() + coefficients.getE() * pw.y()
            + coefficients.getF();
    }
}
