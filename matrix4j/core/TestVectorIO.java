package matrix4j.core;
import matrix4j.core.VectorIO;


public class TestVectorIO {
    static void testLoad() {
        String filename = "testfiles/vector_src.txt";
        Vector v = VectorIO.load(filename);
        System.out.println(v);
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
