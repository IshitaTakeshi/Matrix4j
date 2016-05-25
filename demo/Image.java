public class Image {
    private double[][][] image;
    private int n_channels;

    public Image(double[][][] image) {
        if(image.length == 0) {
            throw new IllegalArgumentException("Empty image is not allowed.");
        }

        this.image = image;
        this.n_channels = image[0][0].length;
    }

    public int getHeight() {
        return this.image.length;
    }

    public int getWidth() {
        return this.image[0].length;
    }

    public int getNChannels() {
        return this.n_channels;
    }

    public double[][] getChannel(int channel) {
        if(channel < 0 || this.n_channels <= channel) {
            throw new IllegalArgumentException(
                "Cannot access channel " + channel + ".");
        }

        final int height = this.getHeight();
        final int width = this.getWidth();

        double[][] m = new double[height][width];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                m[y][x] = image[y][x][channel];
            }
        }
        return m;
    }

    public double[] getPixel(int x, int y) {
        return this.image[y][x];
    }

    public double[] setPixel(int x, int y, double[] pixel) {
        if(pixel.length != this.n_channels) {
            throw new IllegalArgumentException(
                "The number of channels don't match");
        }

        return this.image[y][x] = pixel;
    }
}
