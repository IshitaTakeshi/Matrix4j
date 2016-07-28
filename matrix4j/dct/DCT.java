package matrix4j.dct;

import java.lang.Math;
import static matrix4j.core.Math.ones;
import static matrix4j.core.Math.multiply;
import static matrix4j.core.Math.product;
import matrix4j.core.Matrix;
import matrix4j.core.Vector;


public class DCT {
    //1d DCT
    public static Matrix dct(Vector v) {
        int N = v.length();
        Matrix m = new Matrix(1, N);
        m.setRow(0, v);  //represent the vector as a 1xN matrix
        Matrix c = new DCTMatrix(N);
        return product(c, m);
    }

    //2d DCT
    public static Matrix dct(Matrix m) {
        if(m.nRows() != m.nColumns()) {
            throw new IllegalArgumentException("Matrix must be square");
        }

        int N = m.nRows();
        Matrix c = new DCTMatrix(N);
        return product(product(c.T(), m), c);
    }
}


class DCTMatrix extends Matrix {
    public DCTMatrix(int N) {
        super(N, N);

        this.setRow(0, multiply(ones(N), Math.sqrt(1.0/N)));
        for(int k = 1; k < N; k++) {
            for(int n = 0; n < N; n++) {
                double v = Math.sqrt(2.0/N) * Math.cos((2*n+1)*k*Math.PI/(2*N));
                this.set(k, n, v);
            }
        }
    }
}
