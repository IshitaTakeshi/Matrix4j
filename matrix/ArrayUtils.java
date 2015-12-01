package matrix;


//This class is accesible from methods in the same package
//Because generics don't handle primitives, the methods cannot be
//written using Generics
class ArrayUtils {
    //Copy the given array
    static double[] copy(double[] array) {
        double[] new_array = new double[array.length];
        for(int i = 0; i < array.length; i++) {
            new_array[i] = array[i];
        }
        return new_array;
    }

    static boolean areSame(double[][] a, double[][] b) {
        if(a.length != b.length) {
            return false;
        }

        for(int i = 0; i < a.length; i++) {
            if(!areSame(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    static boolean areSame(double[] a, double[] b) {
        if(a.length != b.length) {
            return false;
        }
        for(int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
