import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class One {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>(); // Create an ArrayList to store lines

        try (BufferedReader reader = new BufferedReader(new FileReader("One.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); // Add each line to the ArrayList
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int increases = 0;
        int currentMeasurement = Integer.parseInt(lines.get(0));
        for (String stringDepth : lines) {
            int depth = Integer.parseInt(stringDepth);
            if (currentMeasurement < depth) {
                increases++;
            }
            currentMeasurement = depth;
        }

        System.out.println("Total increases: " + increases);

        int sums = 0;
        currentMeasurement = Integer.parseInt(lines.get(0)) + Integer.parseInt(lines.get(1)) + Integer.parseInt(lines.get(2));
        for (int i = 3; i < lines.size(); i++) {
            int depth = Integer.parseInt(lines.get(i - 2)) + Integer.parseInt(lines.get(i - 1)) + Integer.parseInt(lines.get(i));
            if (currentMeasurement < depth) {
                sums++;
            }
            currentMeasurement = depth;
        }
        
        System.out.println("Larger sums: " + sums);
    }
}