package matrix4j.linalg;

import java.util.Arrays;

import static matrix4j.core.Math.product;
import static matrix4j.core.Math.multiply;
import matrix4j.core.Matrix;
import matrix4j.core.Vector;
import matrix4j.linalg.Eigen;


public class TestEigen {
    public static void testEigenValueVectors() {
        // Matrix M = new Matrix(new double[][]{
        //     {2, 1, 3},
        //     {1, 2, 3},
        //     {3, 3, 20}
        // });

        Matrix M = new Matrix(new double[][]{
            {-2, 1},
            {1, -2}
        });
        Eigen e = new Eigen(M);

        for(int i = 0; i < e.values().length; i++) {
            System.out.println("vector " + i + " : " + e.vectors()[i] +
                               "   value " + i + " : " + e.values()[i]);
        }
    }

    public static void main(String args[]) {
        testEigenValueVectors();
    }
}
