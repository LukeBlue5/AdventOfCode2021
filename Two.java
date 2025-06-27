import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Two {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>(); // Create an ArrayList to store lines

        try (BufferedReader reader = new BufferedReader(new FileReader("Two.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); // Add each line to the ArrayList
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int distance = 0;
        int depth = 0;

        for (String step : lines) {
            String[] parts = step.split(" ");
            String direction = parts[0];
            int value = Integer.parseInt(parts[1]);

            switch (direction) {
                case "forward":
                    distance += value;
                    break;
                case "down":
                    depth += value;
                    break;
                case "up":
                    depth -= value;
                    break;
            }
        }

        System.out.println("\nMultiple: " + distance * depth);

        distance = 0;
        depth = 0;
        int aim = 0;

        for (String step : lines) {
            String[] parts = step.split(" ");
            String direction = parts[0];
            int value = Integer.parseInt(parts[1]);

            switch (direction) {
                case "forward":
                    distance += value;
                    depth += aim * value; // Adjust depth based on aim
                    break;
                case "down":
                    aim += value;
                    break;
                case "up":
                    aim -= value;
                    break;
            }
        }

        System.out.println("\nMultiple: " + distance * depth);
    }
}