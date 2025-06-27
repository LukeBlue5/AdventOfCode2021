import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Three {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Three.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize variables for the first part of the problem
        int ones = 0;
        int zeros = 0;
        String gamma = "";
        String epsilon = "";
        int binaryLength = lines.get(0).length();

        for (int i = 0; i < binaryLength; i++) {
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).charAt(i) == '1') {
                    ones++;
                }
                else {
                    zeros++;
                }
            }

            if (ones > zeros) {
                gamma += "1";
                epsilon += "0";
            } else {
                gamma += "0";
                epsilon += "1";
            }

            ones = 0; // Reset ones for the next line
            zeros = 0; // Reset zeros for the next line
        }

        System.out.println("\nMultiple: " + Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));

        List<String> onesList = new ArrayList<>();
        List<String> zerosList = new ArrayList<>();
        List<String> generatorList = new ArrayList<>();
        List<String> scrubberList = new ArrayList<>();
        int i = 0;
        int j = 0;
        ones = 0;
        zeros = 0;

        for (j = 0; j < lines.size(); j++) {
            if (lines.get(j).charAt(i) == '1') {
                onesList.add(lines.get(j));
                ones++;
            }
            else {
                zerosList.add(lines.get(j));
                zeros++;
            }
        }

        if (zeros > ones) {
            generatorList.addAll(zerosList);
            scrubberList.addAll(onesList);
        } else {
            generatorList.addAll(onesList);
            scrubberList.addAll(zerosList);
        }

        System.out.println("\nLife Support Rating: " + Integer.parseInt(reduceList(generatorList, binaryLength, false), 2) * Integer.parseInt(reduceList(scrubberList, binaryLength, true), 2));
    }

    private static String reduceList(List<String> list, int length, boolean addZeros) {
        List<String> onesList = new ArrayList<>();
        List<String> zerosList = new ArrayList<>();
        int ones = 0;
        int zeros = 0;
        int i = 1; // Start from the second character for the next calculations
        int j = 0;
        while (list.size() > 1) {
            for (; i < length; i++) {
                onesList.clear();
                zerosList.clear();
                ones = 0;
                zeros = 0;
                j = 0;
                for (; j < list.size(); j++) {
                    if (list.get(j).charAt(i) == '1') {
                        onesList.add(list.get(j));
                        ones++;
                    } else {
                        zerosList.add(list.get(j));
                        zeros++;
                    }
                }

                list.clear(); // Clear the list for the next iteration
                
                // Decide which list to add based on the counts of ones and zeros
                if (addZeros) {
                    if (ones >= zeros) {
                        list.addAll(zerosList);
                    } else {
                        list.addAll(onesList);
                    }
                } else {
                    if (zeros > ones) {
                        list.addAll(zerosList);
                    } else {
                        list.addAll(onesList);
                    }
                }

                if (list.size() == 1)
                    break;
            }
        }

        return list.get(0);
    }
}