package matrix;


//exhibit ith row and jth column of x as x[i, j] in comments
class LinearAlgebra {
    static Matrix gaussianElimination(Matrix x) {
        if(x.nRows() > x.nColumns()) {
            throw new IllegalArgumentException(
                "The number of rows must be larger " +
                "than the number of columns");
        }

        System.out.println("Given matrix");
        System.out.println(x);

        int nRows = x.nRows();

        //divide the 0th row by x[0, 0] so that x[0, 0] equals 1
        x.setRow(0, Math.divide(x.row(0), x.get(0, 0)));

        for(int i = 0; i < nRows-1; i++) {
            for(int j = i+1; j < nRows; j++) {
                //divide ith row by its 0th element
                //x[0, :] * x[j, 0]
                double r = x.get(j, i) / x.get(i, i);
                Vector row = Math.multiply(x.row(i), r);
                x.setRow(j, Math.subtract(x.row(j), row));
            }
        }

        System.out.println("Forward elimination");
        System.out.println(x);

        //normalize diagonal components
        for(int i = 0; i < nRows; i++) {
            if(x.get(i, i) == 0) {
                continue;
            }
            x.setRow(i, Math.divide(x.row(i), x.get(i, i)));
        }

        System.out.println("Normalization of diagonal components");
        System.out.println(x);

        //backward elimination
        for(int i = nRows-1; i >= 0; i--) {
            for(int j = i-1; j >= 0; j--) {
                if(x.get(i, i) == 0) {
                    continue;
                }
                double r = x.get(j, i) / x.get(i, i);
                Vector row = Math.multiply(x.row(i), r);
                x.setRow(j, Math.subtract(x.row(j), row));
            }
        }

        System.out.println("Backward elimination");
        System.out.println(x);
        return x;
    }
}
