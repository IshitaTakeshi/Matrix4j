package matrix;

import matrix.ArrayUtils;
import matrix.Vector;
import java.util.Arrays;


public class Matrix {
    //Express rows of the matrix as vectors
    private Vector[] matrix;

    public Matrix(double[][] matrix) {
        this(matrix, false);
    }

    public Matrix(double[][] matrix, boolean copy) {
        this.matrix = new Vector[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            this.matrix[i] = new Vector(matrix[i], copy);
        }
    }

    //create an empty matrix
    public Matrix(int nRows, int nColumns) {
        this.matrix = new Vector[nRows];
        for(int i = 0; i < nRows; i++) {
            this.matrix[i] = new Vector(nColumns);
        }
    }

    //copy self object
    public Matrix copy() {
        return new Matrix(this.toArray(), false);
    }

    //return self matrix as a 2d array
    //the copied object of internal expression is obtained as a 2d array
    public double[][] toArray() {
        int nRows = this.nRows();
        int nColumns = this.nColumns();
        double[][] array = new double[nRows][nColumns];
        for(int i = 0; i < nRows; i++) {
            array[i] = this.row(i).toArray();
        }
        return array;
    }

    //return the number of rows
    public int nRows() {
        return this.matrix.length;
    }

    //return the number of columns
    public int nColumns() {
        return this.matrix[0].length();
    }

    private boolean rowIndexOutOfBounds(int index) {
        return index < 0 || this.nRows() <= index;
    }

    private boolean columnIndexOutOfBounds(int index) {
        return index < 0 || this.nColumns() <= index;
    }

    private void throwIfRowIndexOutOfBounds(int index) {
        if(rowIndexOutOfBounds(index)) {
            String message = String.format(
                "Row index %d is out of bounds for matrix " +
                "with row length %d",
                index, this.nRows());
            throw new IndexOutOfBoundsException(message);
        }
    }

    private void throwIfColumnIndexOutOfBounds(int index) {
        if(columnIndexOutOfBounds(index)) {
            String message = String.format(
                "Column index %d is out of bounds for matrix " +
                "with column length %d",
                index, this.nColumns());
            throw new IndexOutOfBoundsException(message);
        }
    }

    //throw an exception if either of indices is out of bounds
    private void throwIfOutOfBounds(int i, int j) {
        if(rowIndexOutOfBounds(i) || columnIndexOutOfBounds(j)) {
            String message = String.format(
                "index (%d, %d) is out of bounds for matrix " +
                "with shape (%d, %d)",
                i, j, this.nRows(), this.nColumns());
            throw new IndexOutOfBoundsException(message);
        }
    }

    //get an element at the ith row, jth column
    double get(int i, int j) {
        throwIfOutOfBounds(i, j);
        return this.matrix[i].get(j);
    }

    //set an element to the ith row, jth column
    void set(int i, int j, double e) {
        throwIfOutOfBounds(i, j);
        this.matrix[i].set(j, e);
    }

    public String toString() {
        int nRows = this.nRows();

        String s = "";
        for(int i = 0; i < nRows-1; i++) {
            s += this.row(i).toString();
            s += "\n";
        }
        s += this.row(nRows-1).toString();
        return s;
    }

    //transpose of a matrix M can be written as M.T()
    //like a handwritten equasion
    public Matrix T() {
        int nRows = this.nRows();
        int nColumns = this.nColumns();

        Matrix t = new Matrix(nColumns, nRows);
        for(int i = 0; i < nRows; i++) {
            for(int j = 0; j < nColumns; j++) {
                double e = this.get(i, j);
                t.set(j, i, e);
            }
        }
        return t;
    }

    public double sum() {
        double sum = 0;
        for(Vector row : matrix) {
            sum += row.sum();
        }
        return sum;
    }

    //set array as a row at index
    public void setRow(int index, double[] row) {
        this.setRow(index, new Vector(row));
    }

    public void setRow(int index, Vector row) {
        throwIfRowIndexOutOfBounds(index);
        if(row.length() != this.nColumns()) {
            throw new IllegalArgumentException();
        }
        this.matrix[index] = row;
    }

    //set array as a column at index
    public void setColumn(int index, double[] column) {
        this.setColumn(index, new Vector(column));
    }

    public void setColumn(int index, Vector column) {
        throwIfColumnIndexOutOfBounds(index);
        if(column.length() != this.nRows()) {
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < this.nRows(); i++) {
            this.matrix[i].set(index, column.get(i));
        }
    }

    //returns a row at index
    public Vector row(int index) {
        throwIfRowIndexOutOfBounds(index);
        return this.matrix[index].copy();
    }

    //returns a column at index
    public Vector column(int index) {
        throwIfColumnIndexOutOfBounds(index);

        int nRows = this.nRows();
        double[] column = new double[nRows];
        for(int i = 0; i < nRows; i++) {
            column[i] = this.matrix[i].get(index);
        }
        //create a vector without copying array in the constructor
        return new Vector(column, false);
    }

    //if
    public boolean equals(Matrix another) {
        int nRows = this.nRows();
        if(nRows != another.nRows()) {
            return false;
        }

        for(int i = 0; i < nRows; i++) {
            if(!this.row(i).equals(another.row(i))) {
                return false;
            }
        }
        return true;
    }
}
