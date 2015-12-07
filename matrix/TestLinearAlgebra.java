package matrix;

import matrix.Matrix;
import matrix.LinearAlgebra;


class TestLinearAlgebra {
    private static final Matrix A = new Matrix(new double[][]{
        {2, 4, 2, 8},
        {4, 10, 3, 17},
        {3, 7, 1, 11}
    });

    private static final Matrix B = new Matrix(new double[][]{
        {1, 2, 0, -1},
        {3, 7, -1, 3},
        {2, 2, 1, 2}
    });

    private static final Matrix Aexpected = new Matrix(new double[][]{
        {1, 0, 0, 1},
        {0, 1, 0, 1},
        {0, 0, 1, 1}
    });

    private static final Matrix Bexpected = new Matrix(new double[][]{
        {1, 0, 0, 19},
        {0, 1, 0, -10},
        {0, 0, 1, -16}
    });

    private static final Matrix T = new Matrix(new double[][]{
        {1, 4},
        {4, 1},
        {2, 3}
    });

    static void testGaussJordanElimination() {
        Matrix M;
        M = LinearAlgebra.gaussJordanElimination(A.copy());
        Assert.assertTrue(M.equals(Aexpected));

        M = LinearAlgebra.gaussJordanElimination(B.copy());
        Assert.assertTrue(M.equals(Bexpected));

        boolean exception_occurred= false;
        try {
            LinearAlgebra.gaussJordanElimination(T.copy());
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testGaussianElimination() {
        Matrix M;
        M = LinearAlgebra.gaussianElimination(A.copy());
        Assert.assertTrue(M.equals(Aexpected));

        M = LinearAlgebra.gaussianElimination(B.copy());
        Assert.assertTrue(M.equals(Bexpected));

        boolean exception_occurred= false;
        try {
            LinearAlgebra.gaussianElimination(T.copy());
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void runHomework() {
        Matrix M = new Matrix(new double[][]{
            {1, 2, 3, 4, 22},
            {-3, 3, -2, 2, -14},
            {6, -2, 4, -8, 8},
            {3, -5, 1, 1, 23}
        });
        M = LinearAlgebra.gaussianElimination(M);
        System.out.println("A = " + M.get(0, 4));
        System.out.println("B = " + M.get(1, 4));
        System.out.println("C = " + M.get(2, 4));
        System.out.println("D = " + M.get(3, 4));
    }

    public static void main(String args[]) {
        testGaussJordanElimination();
        testGaussianElimination();
        runHomework();
    }
}
