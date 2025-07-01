import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Four {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Four.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] drawnNumbers = lines.get(0).split(",");
        List<Board> boards = new ArrayList<>();

        for (int i = 2; i < lines.size(); i += 6) {
            List<String> boardLines = new ArrayList<>();
            for (int j = i; j < i + 5; j++) {
                boardLines.add(lines.get(j));
            }
            boards.add(new Board(boardLines));
        }

        Boolean foundWinner = false;

        // Process the drawn numbers and check for winners
        for (String number : drawnNumbers) {
            for (Board board : boards) {
                board.markNumber(number);   // Mark the number on the board

                // Check if the board is a winner
                if (board.isWinner()) {
                    foundWinner = true;
                    System.out.println("Score: " + board.getScore(number)); // Calculate the score for the winning board
                    break; // Exit the loop if a winner is found
                }
            }
            if (foundWinner) {
                break; // Exit the loop if a winner is found
            }
        }

        int winningBoards = 1;
        Boolean foundLastWinner = false;

        for (String number : drawnNumbers) {
            for (int i = 0; i < boards.size(); i++) {
                Board board = boards.get(i); // Get the last board
                if (board.isWinner()) {
                    continue; // Skip already winning boards
                }
                board.markNumber(number);   // Mark the number on the board

                // Check if the board is a winner
                if (board.isWinner()) {
                    winningBoards++;
                    if (winningBoards == boards.size()) {
                        foundLastWinner = true;
                        System.out.println("Last winning board score: " + board.getScore(number)); // Calculate the score for the last winning board
                        break; // Exit the loop if the last winner is found
                    }  
                }
            }
            if (foundLastWinner) {
                break; // Exit the loop if the last winner is found
            }
        }
    }
}

final class Board {
    private List<List<String>> rows;
    private List<List<String>> columns;
    private List<String> markedNumbers;

    public Board(List<String> boardLines) {
        markedNumbers = new ArrayList<>();
        rows = new ArrayList<>();
        columns = new ArrayList<>();

        // Initialize rows
        for (String line : boardLines) {
            List<String> row = new ArrayList<>();
            String[] numbers = line.trim().split("\\s+");
            for (String number : numbers) {
                row.add(number);
            }
            rows.add(row);
        }

        // Initialize columns
        for (int i = 0; i < rows.get(0).size(); i++) {
            List<String> column = new ArrayList<>();
            for (List<String> row : rows) {
                column.add(row.get(i));
            }
            columns.add(column);
        }
    }

    public void markNumber(String number) {
        markedNumbers.add(number);
    }

    public Boolean isWinner() {
        // Check if any row or column is fully marked
        for (List<String> row : rows) {
            if (markedNumbers.containsAll(row)) {
                return true;
            }
        }
        for (List<String> column : columns) {
            if (markedNumbers.containsAll(column)) {
                return true;
            }
        }
        return false;
    }

    public int getScore(String lastDrawnNumber) {
        int score = 0;
        for (List<String> row : rows) {
            for (String number : row) {
                if (!markedNumbers.contains(number)) {
                    score += Integer.parseInt(number);
                }
            }
        }
        return score * Integer.parseInt(lastDrawnNumber);
    }
}