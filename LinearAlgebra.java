package matrix;


//exhibit ith row and jth column of x as x[i, j] in comments
class LinearAlgebra {
    static Matrix gaussianElimination(Matrix x) {
        if(x.nRows() > x.nColumns()) {
            throw new IllegalArgumentException(
                "The number of rows must be larger " +
                "than the number of columns");
        }

        int nRows = x.nRows();

        //divide the 0th row by x[0, 0] so that x[0, 0] equals 1
        x.setRow(0, x.row(0).divide(x.get(0, 0)));

        for(int i = 0; i < nRows-1; i++) {
            for(int j = i+1; j < nRows; j++) {
                //divide ith row by its 0th element
                //x[0, :] * x[j, 0]
                double r = x.get(j, i) / x.get(i, i);
                Vector row = x.row(i).multiply(r);
                x.setRow(j, x.row(j).subtract(row));
            }
        }

        System.out.println("Forward elimination");
        System.out.println(x);

        //normalize diagonal components
        for(int i = 0; i < nRows; i++) {
            if(x.get(i, i) == 0) {
                continue;
            }
            x.setRow(i, x.row(i).divide(x.get(i, i)));
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
                Vector row = x.row(i).multiply(r);
                x.setRow(j, x.row(j).subtract(row));
            }
        }

        System.out.println("Backward elimination");
        System.out.println(x);
        return x;
    }

    public static void main(String[] args) {
        Matrix x = new Matrix(new double[][]{
            {2, 4, 2, 8},
            {4, 10, 3, 17},
            {3, 7, 1, 11}
        });

        gaussianElimination(x);
    }
}
