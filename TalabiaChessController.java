import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TalabiaChessController class serves as the controller in the Model-View-Controller (MVC) design pattern.
// It manages the communication between the model and the view, handling button events and updating the model accordingly.
// (Ku Jing Hao)
public class TalabiaChessController {
    private TalabiaChessModel model;
    private TalabiaChessView view;

    // Constructor for the controller, initializing the model, view, and setting up button listeners
    //(Ku Jing Hao)
    public TalabiaChessController(TalabiaChessModel model, TalabiaChessView view) {
        this.model = model;
        this.view = view;

        initializeButtonsListeners();
        model.addObserver(view); // Registering the view as an observer to the model
    }

    // Method to set up action listeners for various buttons in the view
    //(Ku Jing Hao)
    private void initializeButtonsListeners() {
        // Start button listener
        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStartButton();
            }
        });

        // Save button listener
        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveButton();
            }
        });

        // Load button listener
        view.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoadButton();
            }
        });

        // Restart button listener
        view.getRestartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestartButton();
            }
        });

        // Board buttons listeners (for each cell on the chessboard)
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                final int finalRow = row;
                final int finalCol = col;
                view.getBoardButton(row, col).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Delegate the action to the controller
                        handleBoardButton(finalRow, finalCol);
                    }
                });
            }
        }
    }

    // Method to handle the "Start" button click
    //(Ku Jing Hao)
    private void handleStartButton() {
        view.getStartButton().setEnabled(false);
        model.setGameStart(true);
    }

    // Method to handle the "Save" button click
    //(Ku Jing Hao)
    private void handleSaveButton() {
        // Show an input dialog to get the desired file name from the user
        String fileName = JOptionPane.showInputDialog("Enter file name for the saved game:");

        // Check if the user provided a file name
        if (fileName != null && !fileName.isEmpty()) {
            // Use the singleton instance to save the game
            model.saveGame(fileName + ".txt");
            System.out.println("Game saved to: " + fileName + ".txt");
        } else {
            // Inform the user that no file name was provided
            System.out.println("No file name provided. Game not saved.");
        }
    }

    // Method to handle the "Restart" button click
    //(Ng Min Hoong)
    private void handleRestartButton() {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to restart the game?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            model.clearChessboard();
        }
    }

    // Method to handle the "Load" button click
    //(Ku Jing Hao)
    private void handleLoadButton() {
        // Show an input dialog to get the desired file name from the user
        String fileName = JOptionPane.showInputDialog("Enter file name for the load game:");

        // Check if the user provided a file name
        if (fileName != null && !fileName.isEmpty()) {
            // Use the singleton instance to load the game
            model.loadGame(fileName + ".txt");
            view.update();
            model.rotateAllIcon();
            System.out.println("Game loaded from: " + fileName + ".txt");
        } else {
            // Inform the user that no file name was provided
            System.out.println("No file name provided. Game not loaded.");
        }
    }

    // Method to handle a click on a cell in the chessboard
    //(Ku Jing Hao)
    private void handleBoardButton(int row, int col) {
        if (model.getGameStart()) {
            if (model.getCurrentPiece() == null) {
                // No piece selected, select the clicked piece
                model.selectPiece(row, col);
            } else {
                // Check if the clicked button corresponds to the same piece that is already selected
                if (model.getCurrentPiece().getPosition().getRow() == row &&
                        model.getCurrentPiece().getPosition().getCol() == col) {
                    // Toggle the selection off
                    model.selectPiece(row, col);
                } else {
                    // Move the piece to the clicked position
                    model.movePiece(row, col);
                }
            }
        } else {
            System.out.println("Game has not started yet. Click 'Start' to begin.");
        }
    }
}
