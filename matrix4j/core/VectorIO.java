package matrix4j.core;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import matrix4j.core.Vector;


public class VectorIO {
    public static Vector load(String filename) {
        BufferedReader reader = null;
        List<Double> array_list = new ArrayList<>();
        String line;

        try {
            reader = new BufferedReader(new FileReader(filename));
            line = reader.readLine();
            while(line != null) {
                Double v = Double.parseDouble(line);  // TODO TEST
                array_list.add(v);
                line = reader.readLine();
            }
        } catch(IOException e) {
            System.out.println(e);
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                System.out.println(e);
            }
        }

        double[] array = new double[array_list.size()];
        for(int i = 0; i < array_list.size(); i++) {
            array[i] = array_list.get(i);
        }
        return new Vector(array);
    }

    public static void save(String filename, Vector vector) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            for(int i = 0; i < vector.length(); i++) {
                writer.write(vector.get(i) + "\n");
            }
        } catch(IOException e) {
            System.out.println(e);
        } finally {
            try {
                writer.close();
            } catch(IOException e) {
                System.out.println(e);
            }
        }
    }
}
