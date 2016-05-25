package matrix4j.core;


public class Math {
    public static Vector add(Vector A, Vector B) {
        if(A.length() != B.length()) {
            throw new IllegalArgumentException();
        }

        int length = A.length();
        Vector sum = new Vector(length);
        for(int i = 0; i < length; i++) {
            double a = A.get(i);
            double b = B.get(i);
            sum.set(i, a+b);
        }
        return sum;
    }

    //multiply with a scalar
    public static Vector multiply(Vector A, double r) {
        int length = A.length();
        Vector product = new Vector(length);
        for(int i = 0; i < length; i++) {
            double a = A.get(i);
            product.set(i, r*a);
        }
        return product;
    }

    //element-wise multiplication
    public static Vector multiply(Vector A, Vector B) {
        if(A.length() != B.length()) {
            throw new IllegalArgumentException();
        }

        int length = A.length();
        Vector product = new Vector(length);
        for(int i = 0; i < length; i++) {
            double a = A.get(i);
            double b = B.get(i);
            product.set(i, a*b);
        }
        return product;
    }

    //take dot product
    public static double product(Vector A, Vector B) {
        if(A.length() != B.length()) {
            throw new IllegalArgumentException();
        }

        //the product is the sum of element-wise multiplication
        return sum(multiply(A, B));
    }

    //A - B = A + (-B)
    public static Vector subtract(Vector A, Vector B) {
        return add(A, multiply(B, -1));
    }

    // divide by a given scalar element-wise
    public static Vector divide(Vector A, double r) {
        if(r == 0) {
            throw new IllegalArgumentException();
        }
        return multiply(A, 1/r);
    }

    public static double max(Vector A) {
        return A.max();
    }

    public static int argmax(Vector A) {
        return A.argmax();
    }

    public static Vector abs(Vector A) {
        return A.abs();
    }

    public static double sum(Vector A) {
        return A.sum();
    }

    //returns a vector of given length, filled with ones
    public static Vector ones(int length) {
        double[] ones = new double[length];
        for(int i = 0; i < length; i++) {
            ones[i] = 1;
        }
        return new Vector(ones);
    }

    public static Matrix add(Matrix A, Matrix B) {
        if(!(A.nRows() == B.nRows() &&
             A.nColumns() == B.nColumns())) {
            //TODO select suitable exception
            throw new IllegalArgumentException();
        }

        int nRows = A.nRows();
        int nColumns = A.nColumns();
        Matrix sum = new Matrix(nRows, nColumns);

        for(int i = 0; i < nRows; i++) {
            sum.setRow(i, add(A.row(i), B.row(i)));
        }
        return sum;
    }

    //A - B = A + (-1) * B
    public static Matrix subtract(Matrix A, Matrix B) {
        return add(A, multiply(B, -1));
    }

    //multiply with a scalar
    public static Matrix multiply(Matrix A, double r) {
        int nRows = A.nRows();
        int nColumns = A.nColumns();
        Matrix product = new Matrix(nRows, nColumns);

        for(int i = 0; i < nRows; i++) {
            product.setRow(i, multiply(A.row(i), r));
        }
        return product;
    }

    //element-wise multiplication
    public static Matrix multiply(Matrix A, Matrix B) {
        if(A.nRows() != B.nRows() ||
           A.nColumns() != B.nColumns()) {
            throw new IllegalArgumentException(
                "Given matrix doesn't have the identical shape");
        }

        int nRows = A.nRows();
        int nColumns = A.nColumns();
        Matrix product = new Matrix(nRows, nColumns);

        for(int i = 0; i < nRows; i++) {
            product.setRow(i, multiply(A.row(i), B.row(i)));
        }
        return product;
    }

    //compute matrix multiplication
    public static Matrix product(Matrix A, Matrix B) {
        if(A.nColumns() != B.nRows()) {
            throw new IllegalArgumentException();
        }

        int nRows = A.nRows();
        int nColumns = B.nColumns();
        Matrix product = new Matrix(nRows, nColumns);
        for(int i = 0; i < nRows; i++) {
            for(int j = 0; j < nColumns; j++) {
                product.set(i, j, product(A.row(i), B.column(j)));
            }
        }
        return product;
    }

    public static Matrix divide(Matrix A, double r) {
        if(r == 0) {
            throw new ArithmeticException("division by zero");
        }
        return multiply(A, 1/r);
    }

    //TODO
    //Division requires an inverse matrix
    //public static Matrix divide(Matrix B) {
    //}

    public static double sum(Matrix A) {
        return A.sum();
    }
}
