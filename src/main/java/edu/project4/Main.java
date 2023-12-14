package edu.project4;

import edu.project4.PostProcessing.GammaLogImageProcessor;
import edu.project4.PostProcessing.ImageProcessor;
import edu.project4.Renderers.FractalRenderer;
import edu.project4.Renderers.MultiThreadFractalRenderer;
import edu.project4.Transformations.SphereTransformation;
import edu.project4.Transformations.Transformation;
import edu.project4.shared.FractalImage;
import edu.project4.shared.ImageFormat;
import edu.project4.shared.ImageUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private Main() {}

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {

        FractalImage fi = FractalImage.create(1920, 1080);
        FractalRenderer renderer = new MultiThreadFractalRenderer(5);

        List<Transformation> transformationArrayList = new ArrayList<>();
        transformationArrayList.add(new SphereTransformation());

        Random random = new Random();
        fi = renderer.render(fi, transformationArrayList, 30, 30000, 2, random.nextLong());

        ImageProcessor ip = new GammaLogImageProcessor();
        ip.process(fi);

        ImageUtils.save(fi, "image.png", ImageFormat.PNG);
    }
}
