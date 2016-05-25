import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import matrix4j.dct.DCT;
import matrix4j.core.Matrix;


public class DSP {
    public static Image load(String filename) {
        BufferedImage image = loadImage(filename);
        double[][][] array = imageToArray(image);
        return new Image(array);
    }

    public static BufferedImage loadImage(String filename) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return image;
    }

    private static double[][][] imageToArray(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        double[][][] array = new double[height][width][3];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color c = new Color(image.getRGB(x, y));
                array[y][x][0] = (double)c.getRed() / 255;
                array[y][x][1] = (double)c.getGreen() / 255;
                array[y][x][2] = (double)c.getBlue() / 255;
            }
        }
        return array;
    }

    public static void main(String args[]) {
        String filename = args[0];
        Image image = load(filename);
        Image ycbcr = RGB2YCbCr.convert(image);
        Matrix y = new Matrix(ycbcr.getChannel(0));
        Matrix dct = DCT.dct(y);
        System.out.println(dct);
    }
}
