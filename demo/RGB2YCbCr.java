import matrix4j.core.Matrix;
import matrix4j.core.Vector;
import static matrix4j.core.Math.product;


public class RGB2YCbCr {
    public static Image convert(Image rgb) {
        if(rgb.getNChannels() != 3) {
            throw new IllegalArgumentException(
                "Image must have exactly 3 channels");
        }

        Matrix c = new Matrix(new double[][]{
            {65.481, 128.553, 24.966},
            {-37.797, -74.203, 112.0},
            {112.0, -93.786, -18.214}
        });

        final int height = rgb.getHeight();
        final int width = rgb.getWidth();
        Image ycbcr = new Image(new double[height][width][3]);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                double[] pixel = rgb.getPixel(y, x);
                Matrix m = new Matrix(3, 1);
                m.setColumn(0, pixel);
                double[][] p = product(c, m).T().toArray();
                ycbcr.setPixel(x, y, p[0]);
            }
        }

        return ycbcr;
    }
}
