package matrix4j.core;

import matrix4j.core.Vector;
import matrix4j.core.Assert;
import matrix4j.core.Array;
import matrix4j.core.Math;


class TestVector {
    static final double[] a = {1, 2, 3};
    static final double[] b = {1, 2, 3};
    static final double[] c = {1, -3, 4};
    static final double[] d = {1, 2, -4, 5};
    static final double[] e = {1, 6, -7, 2};

    static final Vector A = new Vector(a);
    static final Vector B = new Vector(b);
    static final Vector C = new Vector(c);
    static final Vector D = new Vector(d);
    static final Vector E = new Vector(e);
    static final Vector empty = new Vector(new double[]{});

    //TODO
    static void testConstructor() {
    }

    static void testEquals() {
        Assert.assertTrue(A.equals(B));
        Assert.assertTrue(!A.equals(C));

        Vector E = new Vector(new double[]{1/3., 2/3., -1/6., 5/6.});
        Vector F = new Vector(new double[]{1/3., 2/3., -1/6., 5/6.});
        Assert.assertTrue(E.equals(F));
    }

    static void testCopy() {
        Assert.assertTrue(A.equals(A.copy()));
        Assert.assertTrue(A.copy().equals(B));
    }

    static void testToArray() {
        Assert.assertTrue(ArrayUtils.areSame(A.toArray(), a));
    }

    static void testLength() {
        Assert.assertTrue(A.length() == a.length);
    }

    static void testGet() {
        for(int i = 0; i < a.length; i++) {
            Assert.assertTrue(A.get(i) == a[i]);
        }

        boolean exception_occurred;
        exception_occurred = false;
        try {
            // index out of bounds error is expected
            A.get(-1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);

        exception_occurred = false;
        try {
            // index out of bounds error is expected
            A.get(3);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testSet() {
        Vector Ac = A.copy();  //not to change the global object
        Ac.set(1, -3);  //A[1] = 3
        Ac.set(2, 4);  //A[2] = 4
        Assert.assertTrue(Ac.equals(C));

        boolean exception_occurred;

        exception_occurred = false;
        try {
            // index out of bounds error is expected
            A.set(-1, 1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);

        exception_occurred = false;
        try {
            // index out of bounds error is expected
            A.set(3, 1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testToString() {
        Assert.assertTrue(A.toString().equals("[  1.000   2.000   3.000]"));
        Assert.assertTrue(empty.toString().equals("[]"));
    }

    static void testAdd() {
        Vector S = Math.add(A, C);
        Vector T = new Vector(new double[]{2, -1, 7});
        Assert.assertTrue(S.equals(T));

        boolean exception_occurred = false;
        try {
            Math.add(A, D);
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testMultiplyScalar() {
        Vector S = Math.multiply(A, 3);
        Vector T = new Vector(new double[]{3, 6, 9});
        Assert.assertTrue(S.equals(T));
    }

    static void testMultiplyElementWise() {
        Vector S = Math.multiply(A, C);
        Vector T = new Vector(new double[]{1, -6, 12});
        Assert.assertTrue(S.equals(T));

        boolean exception_occurred = false;
        try {
            Math.multiply(A, D);
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testMax() {
        Assert.assertTrue(A.max() == 3);
        Assert.assertTrue(D.max() == 5);
        Assert.assertTrue(E.max() == 6);
        Assert.assertTrue(Math.max(A) == 3);
        Assert.assertTrue(Math.max(D) == 5);
        Assert.assertTrue(Math.max(E) == 6);

        boolean exception_occurred = false;
        try {
            empty.max();
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testArgmax() {
        Assert.assertTrue(A.argmax() == 2);
        Assert.assertTrue(D.argmax() == 3);
        Assert.assertTrue(E.argmax() == 1);
        Assert.assertTrue(Math.argmax(A) == 2);
        Assert.assertTrue(Math.argmax(D) == 3);
        Assert.assertTrue(Math.argmax(E) == 1);

        boolean exception_occurred = false;
        try {
            empty.argmax();
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testAbs() {
        Vector Aexpected = new Vector(new double[]{1, 2, 3});
        Vector Dexpected = new Vector(new double[]{1, 2, 4, 5});
        Assert.assertTrue(A.abs().equals(Aexpected));
        Assert.assertTrue(D.abs().equals(Dexpected));
        Assert.assertTrue(Math.abs(A).equals(Aexpected));
        Assert.assertTrue(Math.abs(D).equals(Dexpected));
    }

    static void testSlice() {
        Vector expected;

        Assert.assertTrue(A.slice(0, A.length()).equals(A));
        Assert.assertTrue(Array.slice(A, 0, A.length()).equals(A));

        expected = new Vector(new double[]{2, -4});
        Assert.assertTrue(D.slice(1, 3).equals(expected));
        Assert.assertTrue(Array.slice(D, 1, 3).equals(expected));

        Assert.assertTrue(Array.slice(D, 2, 2).equals(empty));

        boolean exception_occurred;
        exception_occurred = false;
        try {
            Array.slice(A, -1, 1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);

        exception_occurred = false;
        try {
            Array.slice(A, 2, A.length()+1);
        } catch(IndexOutOfBoundsException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);

        exception_occurred = false;
        try {
            Array.slice(A, 2, 1);
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testSum() {
        Assert.assertTrue(A.sum() == 6);
        Assert.assertTrue(D.sum() == 4);
        Assert.assertTrue(Math.sum(A) == 6);
        Assert.assertTrue(Math.sum(D) == 4);
    }

    static void testNorm() {
        Assert.assertTrue(Math.norm(new Vector(new double[]{3, 4})) == 5);
        Assert.assertTrue(Math.norm(new Vector(new double[]{1, 0, 0})) == 1);
    }

    static void testProduct() {
        Assert.assertTrue(Math.product(A, C) == 7);

        boolean exception_occurred = false;
        try {
            Math.product(A, D);
        } catch(IllegalArgumentException e) {
            exception_occurred = true;
        }
        Assert.assertTrue(exception_occurred);
    }

    static void testSubtract() {
        Vector S = Math.subtract(A, C);
        Vector T = new Vector(new double[]{0, 5, -1});
        Assert.assertTrue(S.equals(T));
    }

    static void testDivideByScalar() {
        Vector S = Math.divide(A, 3);
        Vector T = new Vector(new double[]{1/3., 2/3., 1});
        Assert.assertTrue(S.equals(T));
    }

    static void testOnes() {
        int N = 5;
        Vector ones = Math.ones(N);
        for(int i = 0; i < N; i++) {
            Assert.assertTrue(ones.get(i) == 1);
        }
    }

    public static void main(String[] args) {
        testEquals();
        testCopy();
        testToArray();
        testLength();
        testSet();
        testGet();
        testToString();
        testAdd();
        testMultiplyScalar();
        testMultiplyElementWise();
        testMax();
        testArgmax();
        testAbs();
        testSlice();
        testSum();
        testNorm();
        testProduct();
        testSubtract();
        testDivideByScalar();
        testOnes();
    }
}
