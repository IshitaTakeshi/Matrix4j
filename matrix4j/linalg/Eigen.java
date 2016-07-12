package matrix4j.linalg;

import matrix4j.core.Matrix;
import matrix4j.core.Vector;
import matrix4j.core.RandomArray;
import static matrix4j.core.Math.product;
import static matrix4j.core.Math.multiply;
import static matrix4j.core.Math.subtract;
import static matrix4j.core.Math.abs;
import static matrix4j.core.Math.sum;
import static matrix4j.core.Math.divide;
import static matrix4j.core.Math.norm;


public class Eigen {
    private Vector[] vectors;
    private double[] values;

    public Eigen(Matrix M) {
        if(M.nRows() != M.nColumns()) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        this.values = new double[M.nRows()];
        this.vectors = new Vector[M.nRows()];
        this.eigenValueVectors(M);
    }

    private void eigenValueVectors(Matrix M) {
        for(int i = 0; i < M.nRows(); i++) {
            this.powerIteration(i, M, 800, 1e-4);
            double lambda = this.values[i];
            Matrix v = new Matrix(M.nRows(), 1);
            v.setColumn(0, this.vectors[i]);
            M = subtract(M, multiply(product(v, v.T()), lambda));
        }
    }

    private void powerIteration(int index, Matrix M,
                                int max_iteration, double threshold) {
        Matrix x = new Matrix(M.nRows(), 1);
        x.setColumn(0, new double[]{1, -1}); //RandomArray.doubleArray(M.nColumns()));

        Matrix x_1 = x;
        for(int i = 0; i < max_iteration; i++) {
            x = product(M, x_1);
            x = divide(x, norm(x.column(0)));

            if(sum(abs(subtract(x, x_1))) < threshold) {
                break;
            }
            x_1 = x;
        }

        this.values[index] = product(product(x.T(), M), x).get(0, 0);
        this.vectors[index] = x.column(0);
    }

    public double[] values() {
        return this.values;
    }

    public Vector[] vectors() {
        return this.vectors;
    }
}
