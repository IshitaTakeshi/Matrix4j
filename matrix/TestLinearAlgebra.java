package matrix;

import matrix.Matrix;
import matrix.LinearAlgebra;


class TestLinearAlgebra {
    static void gaussianElimination() {
        Matrix M, T;

        M = new Matrix(new double[][]{
            {2, 4, 2, 8},
            {4, 10, 3, 17},
            {3, 7, 1, 11}
        });

        T = new Matrix(new double[][]{
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {0, 0, 1, 1}
        });

        M = LinearAlgebra.gaussianElimination(M);
        Assert.assertTrue(M.equals(T));

        M = new Matrix(new double[][]{
            {1, 2, 0,-1},
            {3, 7, -1, 3},
            {2, 2, 1, 2}
        });

        T = new Matrix(new double[][]{
            {1, 0, 0, 19},
            {0, 1, 0, -10},
            {0, 0, 1, -16}
        });

        M = LinearAlgebra.gaussianElimination(M);
        Assert.assertTrue(M.equals(T));

        M = new Matrix(new double[][]{
            {1, 4},
            {4, 1},
            {2, 3}
        });

        boolean exception_occurred= false;
        try {
            M = LinearAlgebra.gaussianElimination(M);
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }
    public static void main(String args[]) {
        gaussianElimination();
    }
}
