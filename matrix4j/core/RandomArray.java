package matrix4j.core;


public class RandomArray {
    public static double[] doubleArray(int length) {
        double[] array = new double[length];
        java.util.Random random = new java.util.Random();
        for(int i = 0; i < length; i++) {
            array[i] = random.nextDouble();
        }
        return array;
    }
}
