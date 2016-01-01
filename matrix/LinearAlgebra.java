package matrix;
import matrix.Format;


//exhibit ith row and jth column of x as x[i, j] in comments
class LinearAlgebra {
    static Matrix gaussJordanElimination(Matrix x) {
        throwExceptionIfInvalidShape(x);

        System.out.println("Given matrix");
        System.out.println(x);

        //divide the 0th row by x[0, 0] so that x[0, 0] equals 1
        x.setRow(0, Math.divide(x.row(0), x.get(0, 0)));

        System.out.println("Divide the 0th row by x[0, 0] so that " +
                           "x[0, 0] becomes 1");
        System.out.println(x);

        x = forwardElimination(x);

        System.out.println("The result of forward elimination");
        System.out.println(x);

        x = normalizeDiagonalComponents(x);

        System.out.println("Normalize the diagonal components");
        System.out.println(x);

        x = backwardElimination(x);

        System.out.println("The result of backward elimination");
        System.out.println(x);
        return x;
    }

    static Matrix gaussianElimination(Matrix x) {
        throwExceptionIfInvalidShape(x);

        System.out.println("Given matrix");
        System.out.println(x);

        x = forwardElimination(x);

        System.out.println("The result of forward elimination");
        System.out.println(x);

        x = backwardElimination(x);
        x = normalizeDiagonalComponents(x);
        return x;
    }

    static Matrix forwardElimination(Matrix x) {
        //i: column index
        //j: row index
        for(int i = 0; i < x.nRows()-1; i++) {
            for(int j = i+1; j < x.nRows(); j++) {
                double r = x.get(j, i) / x.get(i, i);
                Vector row = Math.multiply(x.row(i), r);
                x.setRow(j, Math.subtract(x.row(j), row));
            }
        }
        return x;
    }

    static Matrix backwardElimination(Matrix x) {
        for(int i = x.nRows()-1; i >= 0; i--) {
            for(int j = i-1; j >= 0; j--) {
                if(x.get(i, i) == 0) {
                    continue;
                }
                double r = x.get(j, i) / x.get(i, i);
                Vector row = Math.multiply(x.row(i), r);
                x.setRow(j, Math.subtract(x.row(j), row));
            }
        }
        return x;
    }

    static Matrix normalizeDiagonalComponents(Matrix x) {
        //normalize diagonal components
        for(int i = 0; i < x.nRows(); i++) {
            if(x.get(i, i) == 0) {
                continue;
            }
            x.setRow(i, Math.divide(x.row(i), x.get(i, i)));
        }
        return x;
    }

    static void throwExceptionIfInvalidShape(Matrix x) {
        if(x.nRows() > x.nColumns()) {
            throw new IllegalArgumentException(
                "The number of rows must be larger " +
                "than the number of columns");
        }
    }

    static Matrix swapRows(Matrix x, int i, int j) {
        Vector r = x.row(i);
        x.setRow(i, x.row(j));
        x.setRow(j, r);
        return x;
    }

    static Matrix pivotingForwardElimination(Matrix x) {
        //i: column index
        //j: row index
        for(int i = 0; i < x.nRows()-1; i++) {
            //x[i, i] is the pivot
            //swap with the index of the max row in the ith column

            int argmax = x.column(i).slice(i, x.nRows()).abs().argmax();
            x = swapRows(x, i+argmax, i);

            System.out.println("Swap " + Format.ordinal(i+argmax) + " row and " +
                               Format.ordinal(i) + " row");
            System.out.println(x + "\n");

            for(int j = i+1; j < x.nRows(); j++) {
                if(x.get(i, i) == 0) {
                    //Avoid zero division error
                    continue;
                }

                double r = x.get(j, i) / x.get(i, i);
                Vector row = Math.multiply(x.row(i), r);
                x.setRow(j, Math.subtract(x.row(j), row));

                System.out.println("Compare the " + Format.ordinal(i) +
                                   " row and the " + Format.ordinal(j) +
                                   " row");
                System.out.println("Multiply the " + Format.ordinal(i) +
                                   " row by " + r + " and subtract from the " +
                                   Format.ordinal(j) + " row");
                System.out.println(x + "\n");
            }
            System.out.println("\n");
        }
        return x;
    }

    static Matrix partialPivotingElimination(Matrix x) {
        throwExceptionIfInvalidShape(x);

        System.out.println("Given matrix");
        System.out.println(x + "\n\n");

        x = pivotingForwardElimination(x);

        System.out.println("The result of forward elimination");
        System.out.println(x + "\n");

        x = backwardElimination(x);
        x = normalizeDiagonalComponents(x);

        return x;
    }
}
