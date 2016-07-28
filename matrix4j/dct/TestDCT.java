package matrix4j.dct;

import matrix4j.core.Math;
import matrix4j.core.Matrix;
import matrix4j.dct.DCTMatrix;
import matrix4j.dct.DCT;
import matrix4j.core.Assert;


class TestDCT {
    static void testDCTMatrix() {
        Matrix expected = new Matrix(new double[][]{
            {0.5,  0.5,  0.5, 0.5},
            {0.653281482438188, 0.270598050073098, -0.270598050073098, -0.653281482438188},
            {0.5, -0.5, -0.5, 0.5},
            {0.270598050073098, -0.653281482438188, 0.653281482438188, -0.270598050073098},
        });
        DCTMatrix m = new DCTMatrix(4);

        Matrix d = Math.subtract(m, expected);
        System.out.println(m + "\n");
        System.out.println(d + "\n");

        double diff = Math.sum(Math.multiply(d, d));
        Assert.assertTrue(diff < 1E-10);
    }

    static void test1dDCT() {
    }

    static void test2dDCT() {
        //Matrix m = new Matrix(new double[][]{
        //    {1, 1, 1, 1},
        //    {2, 1, 2, 1},
        //    {3, 1, 3, 1},
        //    {4, 1, 4, 1},
        //});
        Matrix m = new Matrix(new double[][]{
            {1, 2, 0},
            {1, 0, 0},
            {0, 0, 0},
        });
        Matrix r = DCT.dct(m);

        System.out.println("2dDCT\n");
        System.out.println(r + "\n");
    }

    public static void testOrthogonal() {
        DCTMatrix c = new DCTMatrix(4);
        System.out.println("DCT Matrix C:\n" + c + "\n");

        Matrix p = Math.product(c.T(), c);
        System.out.println("C^T \\dot C:\n" + p + "\n");
    }

    public static void main(String[] args) {
        testDCTMatrix();
        test2dDCT();
        testOrthogonal();
    }
}
