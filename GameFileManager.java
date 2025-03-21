import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

// GameFileManager is a singleton class responsible for saving and loading game states.
// It follows the Singleton design pattern to ensure a single instance for managing game file operations.
//(Ku Jing Hao)
public class GameFileManager {
    private static GameFileManager instance;

    // Private constructor to prevent direct instantiation
    //(Ku Jing Hao)
    private GameFileManager() {
    }

    // Public method to get the singleton instance
    //(Ku Jing Hao)
    public static GameFileManager getInstance() {
        if (instance == null) {
            instance = new GameFileManager();
        }
        return instance;
    }
    
    // Method to save the game state to a file
    //(Ku Jing Hao)
    public static void saveGame(TalabiaChessModel model, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Save the current player's turn
            writer.println("Current Player's Turn : " + (model.getIsBlueTurn() ? "Blue" : "Yellow"));
            
            // Save the turn count
            writer.println("Current Number of Turn : " + model.getMoveCount());
                
            // Save blueReachedEnd
            writer.println("Blue Point Piece Reached End : " + model.getBlueReachedEnd());

            // Save yellowReachedEnd
            writer.println("Yellow Point Piece Reached End : " + model.getYellowReachedEnd());
            
            // Save the positions of each piece on the board
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7; col++) {
                    if (model.isPieceIconPresent(row, col)) {
                        String pieceIcon = model.getPieceIcon(row, col);
                        writer.println(row + " " + col + " " + pieceIcon);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to load the game state from a file
    //(Ku Jing Hao)
    public static void loadGame(TalabiaChessModel model, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Load the current player's turn
            String currentPlayerTurnLine = reader.readLine();
            boolean isBlueTurn = extractValue(currentPlayerTurnLine).equalsIgnoreCase("Blue");
            model.setBlueTurn(isBlueTurn);

            // Load the move count
            String moveCountLine = reader.readLine();
            int moveCount = Integer.parseInt(extractValue(moveCountLine));
            model.setMoveCount(moveCount);

            // Load blueReachedEnd
            String blueReachedEndLine = reader.readLine();
            List<Integer> blueReachedEnd = parseIntegerList(extractValue(blueReachedEndLine));
            model.setBlueReachedEnd(blueReachedEnd);

            // Load yellowReachedEnd
            String yellowReachedEndLine = reader.readLine();
            List<Integer> yellowReachedEnd = parseIntegerList(extractValue(yellowReachedEndLine));
            model.setYellowReachedEnd(yellowReachedEnd);
            
            // Clear the board before loading the pieces
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7; col++) {
                    model.removePieceIcon(row, col);
                }
            }

            // Load the positions of each piece on the board
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                String pieceIcon = parts[2];
                
                // Place the piece icon on the board
                model.placeLoadPieceIcon(row, col, pieceIcon);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Helper method to parse a comma-separated list of integers from a string
    //(Ku Jing Hao)
    private static List<Integer> parseIntegerList(String line) {
        List<Integer> result = new ArrayList<>();
        
        // Check if the line is not empty
        if (line != null && !line.isEmpty()) {
            // Remove brackets from the input line
            String[] values = line.substring(1, line.length() - 1).split(",");
            
            // Check if the values array is not empty
            if (values.length > 0) {
                for (String value : values) {
                    // Check if the value is not empty before parsing
                    if (!value.trim().isEmpty()) {
                        result.add(Integer.parseInt(value.trim()));
                    }
                }
            }
        }
        
        return result;
    }
    
    // Helper method to extract the value after the colon (:) and trim any leading or trailing spaces
    //(Ku Jing Hao)
    private static String extractValue(String line) {
        return line.substring(line.indexOf(":") + 1).trim();
    }
}