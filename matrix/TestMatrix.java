package matrix;

import matrix.Matrix;
import matrix.Assert;
import java.util.Arrays;


class TestMatrix {
    static final double[][] a = {
        {1, 2, 3},
        {4, 5, 6}
    };
    static final double[][] b = {
        {1, 2, 3},
        {4, 5, 6}
    };
    static final double[][] c = {
        {1, 2, -4},
        {3, -1, 2}
    };
    static final double[][] d = {
        {1, 2},
        {-4, 5},
        {2, 3}
    };

    static final Matrix A = new Matrix(a);
    static final Matrix B = new Matrix(b);
    static final Matrix C = new Matrix(c);
    static final Matrix D = new Matrix(d);

    //TODO
    static void testConstructor() {
    }

    static void testEquals() {
        Assert.assertTrue(A.equals(B));
        Assert.assertTrue(!A.equals(C));
        Assert.assertTrue(!A.equals(D));
    }

    static void testCopy() {
        Assert.assertTrue(A.equals(A.copy()));
        Assert.assertTrue(A.copy().equals(B));
    }

    static void testToArray() {
        Assert.assertTrue(ArrayUtils.areSame(A.toArray(), a));
    }

    static void testNRows() {
        Assert.assertTrue(A.nRows() == 2);
        Assert.assertTrue(D.nRows() == 3);
    }

    static void testNColumns() {
        Assert.assertTrue(A.nColumns() == 3);
        Assert.assertTrue(D.nColumns() == 2);
    }

    static void testGet() {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                Assert.assertTrue(A.get(i, j) == a[i][j]);
            }
        }

        boolean exception_occurred;
        exception_occurred= false;
        try {
            // index out of bounds error is expected
            A.get(2, 1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);

        exception_occurred = false;
        try {
            // index out of bounds error is expected
            A.get(-1, 1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testSet() {
        Matrix Ac = A.copy();  //not to change the global object
        Ac.set(0, 2, -4);  //A[0, 2] = -4
        Ac.set(1, 0, 3);  //A[1, 0] = 3
        Ac.set(1, 1, -1); //A[1, 1] = -1
        Ac.set(1, 2, 2); //A[1, 2] = -2
        Assert.assertTrue(Ac.equals(C));

        boolean exception_occurred;
        // index out of bounds error is expected

        exception_occurred= false;
        try {
            A.set(1, 3, 1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);

        exception_occurred = false;
        try {
            A.set(0, -1, 1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testToString() {
        String s = "[  1.000   2.000   3.000]\n[  4.000   5.000   6.000]\n";
        Assert.assertTrue(A.toString().equals(s));
    }

    static void testT() {
        double[][] t = {
            {1, 4},
            {2, 5},
            {3, 6}
        };
        Matrix T = new Matrix(t);
        Assert.assertTrue(A.T().equals(T));
    }

    static void testSum() {
        Assert.assertTrue(A.sum() == 21);
        Assert.assertTrue(D.sum() == 9);
        Assert.assertTrue(Math.sum(A) == 21);
        Assert.assertTrue(Math.sum(D) == 9);
    }

    static void testAdd() {
        double[][] t = {
            {2, 4, -1},
            {7, 4, 8}
        };

        Matrix T = new Matrix(t);
        Assert.assertTrue(Math.add(A, C).equals(T));

        boolean exception_occurred = false;
        try {
            Math.add(A, D);
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testSubtract() {
        double[][] t = {
            {0, 0, 7},
            {1, 6, 4}
        };
        Matrix T = new Matrix(t);
        Assert.assertTrue(Math.subtract(A, C).equals(T));
    }

    static void testMultiplyScalar() {
        double[][] t = {
            {-3, -6, -9},
            {-12, -15, -18}
        };
        Matrix T = new Matrix(t);
        Assert.assertTrue(Math.multiply(A, -3).equals(T));
    }

    static void testMultiplyElementWise() {
        Matrix S = Math.multiply(A, C);
        double[][] t = {
            {1, 4, -12},
            {12, -5, 12}
        };
        Matrix T = new Matrix(t);
        Assert.assertTrue(S.equals(T));

        boolean exception_occurred = false;
        try {
            Math.multiply(A, D);
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testProduct() {
        double[][] t = {
            {-1, 21},
            {-4, 51}
        };
        Matrix T = new Matrix(t);
        Assert.assertTrue(Math.product(A, D).equals(T));

        boolean exception_occurred = false;
        try {
            Math.product(A, C);
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testDivideByScalar() {
        double[][] t = {
            {1./3, 2./3, 1},
            {4./3, 5./3, 2}
        };
        Matrix T = new Matrix(t);
        Assert.assertTrue(Math.divide(A, 3).equals(T));
    }

    static void testSetRow() {
        Matrix Ac = A.copy();
        Ac.setRow(0, new double[]{1, 2, -4});
        Ac.setRow(1, new double[]{3, -1, 2});
        Assert.assertTrue(Ac.equals(C));

        boolean exception_occurred;
        exception_occurred = false;
        try {
            Ac.setRow(0, new double[]{1, 2});
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);

        exception_occurred = false;
        try {
            Ac.setRow(4, new double[]{1, 2, 3});
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testSetColumn() {
        Matrix Ac = A.copy();
        Ac.setColumn(0, new double[]{1, 3});
        Ac.setColumn(1, new double[]{2, -1});
        Ac.setColumn(2, new double[]{-4, 2});
        Assert.assertTrue(Ac.equals(C));

        boolean exception_occurred;
        exception_occurred = false;
        try {
            Ac.setColumn(0, new double[]{1, 2, 3});
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);

        exception_occurred = false;
        try {
            Ac.setRow(4, new double[]{1, 2, 3});
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testRow() {
        Vector T = new Vector(new double[]{1, 2, 3});
        Assert.assertTrue(A.row(0).equals(T));

        boolean exception_occurred;
        exception_occurred = false;
        try {
            A.row(3);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testColumn() {
        Vector T = new Vector(new double[]{1, 4});
        Assert.assertTrue(A.column(0).equals(T));

        boolean exception_occurred;
        exception_occurred = false;
        try {
            A.column(3);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    public static void main(String[] args) {
        testEquals();
        testCopy();
        testToArray();
        testNRows();
        testNColumns();
        testGet();
        testSet();
        testToString();
        testT();
        testSum();
        testAdd();
        testSubtract();
        testMultiplyScalar();
        testMultiplyElementWise();
        testProduct();
        testDivideByScalar();
        testSetRow();
        testSetColumn();
        testRow();
        testColumn();
    }
}
