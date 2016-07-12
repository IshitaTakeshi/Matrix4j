package matrix4j.core;

import matrix4j.core.VectorIO;
import matrix4j.core.Assert;


public class TestVectorIO {
    static void testLoad() {
        String filename = "testfiles/vector_src.txt";
        Vector v = VectorIO.load(filename);
        Vector u = new Vector(new double[]{
            1, 0, 6, 1, 9, 8, 2, 1, 6, 7
        });
        Assert.assertTrue(v.equals(u));
    }

    static void testSave() {
        String filename = "testfiles/vector_dst.txt";
        Vector v = new Vector(new double[]{0, 1, 2, 3});
        VectorIO.save(filename, v);
    }

    public static void main(String args[]) {
        testLoad();
        testSave();
    }
}
