package matrix;

public class Vector {
    private double[] vector;

    //'no copying' as default
    public Vector(double[] vector) {
        this(vector, false);
    }

    //create the vector object
    //copy: whether copying given array
    public Vector(double[] vector, boolean copy) {
        if(!copy) {
            this.vector = vector;
            return;
        }

        this.vector = ArrayUtils.copy(vector);
    }

    //create an empty vector
    public Vector(int size) {
        this.vector = new double[size];
    }

    //copy the self object
    public Vector copy() {
        return new Vector(this.vector, true);
    }

    //Returns the elements of self as an array
    public double[] toArray() {
        return ArrayUtils.copy(this.vector);
    }

    //the number of elements
    public int length() {
        return this.vector.length;
    }

    public double sum() {
        double s = 0;
        for(double e: this.vector) {
            s += e;
        }
        return s;
    }

    //throw an exception if either of indices is out of bounds
    private void throwExceptionIfOutOfBounds(int index) {
        int length = this.length();
        if(index < 0 || length <= index) {
            throw new IndexOutOfBoundsException(
                String.format(
                    "index %d is out of bounds for vector with length %d",
                    length, length));
        }
    }

    //return an element at index
    public double get(int index) {
        throwExceptionIfOutOfBounds(index);
        return this.vector[index];
    }

    // set element at index
    public void set(int index, double e) {
        throwExceptionIfOutOfBounds(index);
        this.vector[index] = e;
    }

    public String toString() {
        int n = this.length();

        String s = "[";
        for(int i = 0; i < n-1; i++) {
            s += String.format("% 4.3f ", this.get(i));
        }
        s += String.format("% 4.3f]\n", this.get(n-1));
        return s;
    }

    //regard the given matrix same as self if it satisfies:
    //  another object has the same number of elements
    //  all elements are same as self's ones
    public boolean equals(Vector another) {
        if(this.length() != another.length()) {
            return false;
        }

        int length = this.length();
        for(int i = 0; i < length; i++) {

            //casting into float for avoiding the rouding error
            float a = (float)this.get(i);
            float b = (float)another.get(i);
            if(a != b) {
                return false;
            }
        }
        return true;
    }
}
