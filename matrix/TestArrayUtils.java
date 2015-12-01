package matrix;

import matrix.ArrayUtils;
import matrix.Assert;


class TestArrayUtils {
    static void testCopy() {
        double[] array = {1, 2, 3};
        double[] copied = ArrayUtils.copy(array);
        Assert.assertTrue(array.length == copied.length);
        for(int i = 0; i < array.length; i++) {
            Assert.assertTrue(array[i] == copied[i]);
        }
    }

    static void testAreSame1d() {
        double[] array, copied;
        array = new double[]{1, 2, 3};
        copied = new double[]{1, 2, 3};
        Assert.assertTrue(ArrayUtils.areSame(array, copied));

        array = new double[]{1, 2, 3};
        copied = new double[]{1, 2, 3, 4};
        Assert.assertTrue(!ArrayUtils.areSame(array, copied));

        array = new double[]{1, 2, 3};
        copied = new double[]{1, 3, 3};
        Assert.assertTrue(!ArrayUtils.areSame(array, copied));
    }

    static void testAreSame2d() {
        double[][] array, copied;

        array = new double[][]{{1, 2}, {3, 4}};
        copied = new double[][]{{1, 2}, {3, 4}};
        Assert.assertTrue(ArrayUtils.areSame(array, copied));

        array = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        copied = new double[][]{{1, 2}, {3, 4}};
        Assert.assertTrue(!ArrayUtils.areSame(array, copied));

        array = new double[][]{{1, 2}, {3, 3}};
        copied = new double[][]{{1, 2}, {3, 4}};
        Assert.assertTrue(!ArrayUtils.areSame(array, copied));
    }

    public static void main(String[] args) {
        testCopy();
        testAreSame1d();
        testAreSame2d();
    }
}
