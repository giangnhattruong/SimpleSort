package funix.asm1;

import java.io.*;
import java.util.Arrays;

/**
 * FileProcess Class can write an array or a string to a file,
 * and read an array from file (return an array)
 */
public class FileProcess implements Writable, Readable {
    @Override
    public void writeFile(String fileName, float[] array) {
        String arrayString = Arrays.toString(array);
        writeFile(fileName, arrayString.substring(1, arrayString.length() - 1));
    }

    @Override
    public void writeFile(String fileName, String string) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public float[] readFile(String fileName) {
        try (FileReader file = new FileReader(fileName)) {
            BufferedReader reader = new BufferedReader(file);

            return convertToArray(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new float[0];
    }

    /**
     * Convert To Array takes a string of float numbers (ex: 1.0, 2.0, 3.0)
     * then insert these float numbers into an array
     * and return that array
     * @param string - string of float numbers
     * @return - array of float numbers
     */
    private float[] convertToArray(String string) {
        if (string == null)
            return null;

        String[] floatString = string
                .split(", ");
        float[] floats = new float[floatString.length];

        for (int i = 0; i < floats.length; i++)
            floats[i] = Float.parseFloat(floatString[i]);

        return floats;
    }
}
