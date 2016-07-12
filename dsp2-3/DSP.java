package matrix4j.linalg;

import java.util.Arrays;

import static matrix4j.core.Math.product;
import static matrix4j.core.Math.multiply;
import matrix4j.core.Matrix;
import matrix4j.core.Vector;
import matrix4j.linalg.Eigen;


public class DSP {
    public static void main(String[] args) {
        Matrix M = new Matrix(new double[][]{
            {2, 1, 3},
            {1, 2, 3},
            {3, 3, 20}
        });

        Eigen e = new Eigen(M);

        Arrays.toString(e.values());
        for(Vector vector: e.vectors()) {
            System.out.println(vector);
        }
    }
}
