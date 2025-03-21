import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// TalabiaChessView class represents the GUI view of the Talabia Chess game.
// It extends JFrame and implements the Observer interface to receive updates from the model.
// The view consists of a chessboard, buttons for controlling the game, and a status bar.
// It is responsible for initializing the GUI components, handling button events, and updating the UI based on the model.
public class TalabiaChessView extends JFrame implements Observer {
    private TalabiaChessModel model = new TalabiaChessModel();

    private JPanel buttonPanel;
    private JPanel chessBoardPanel;

    public static JButton[][] boardButton;
    private JButton startButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton restartButton;
    protected JLabel statusBarLabel;

    // Constructor for the view, initializing the UI components and registering the view as an observer to the model
    //(Ku Jing Hao)
    public TalabiaChessView() {
        setTitle("Talabia Chess");
        setSize(800, 700);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Confirm exit when closing the window
                int result = JOptionPane.showConfirmDialog(
                        TalabiaChessView.this,
                        "Are you sure you want to exit?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    // If the user clicks 'Yes', close the window
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });

        setLayout(new BorderLayout());

        // Create button panel and chessboard panel
        createButtonPanel();
        createChessBoardPanel();

        // Add status bar at the bottom
        statusBarLabel = new JLabel("Current Turn: " + this.model.getCurrentPlayer().getName());
        add(statusBarLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Registering the view as an observer to the model
        this.model.addObserver(this);

        model.setupPiece(); // Call this after creating the model
    }

    // Method to create the button panel with start, save, load, and restart buttons
    //(Ku Jing Hao)
    private void createButtonPanel() {
        buttonPanel = new JPanel(new GridLayout(1, 5));

        startButton = new JButton("Start"); // Create start button
        saveButton = new JButton("Save");   // Create save button
        loadButton = new JButton("Load");   // Create load button
        restartButton = new JButton("Restart"); // Create restart button

        buttonPanel.add(startButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(restartButton);

        add(buttonPanel, BorderLayout.NORTH);
    }

    // Method to create the chessboard panel with buttons for each cell
    //(Ku Jing Hao)
    private void createChessBoardPanel() {
        boardButton = new JButton[6][7];
        chessBoardPanel = new JPanel();
        chessBoardPanel.setLayout(new GridLayout(6, 7));

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                boardButton[row][col] = new JButton();
                boardButton[row][col].setPreferredSize(new Dimension(80, 80));
                // Set background color based on row and column
                if ((row + col) % 2 == 0) {
                    boardButton[row][col].setBackground(Color.WHITE);
                } else {
                    boardButton[row][col].setBackground(new Color(0xCC, 0xCC, 0xCC));
                }
                chessBoardPanel.add(boardButton[row][col]);
            }
        }

        add(chessBoardPanel, BorderLayout.CENTER);
    }

    // Update method called when the model notifies of changes
    //(Ku Jing Hao)
    @Override
    public void update() {
        // Update the status bar based on the changes in the model
        statusBarLabel.setText("Current Turn: " + this.model.getCurrentPlayer().getName());
        if (model.getGameOver()) {
            statusBarLabel.setText("Game Over. " + model.getCurrentPlayer().getName() + " team wins!");
        }
    }

    // Getter method to access the board button at a specific row and column
    //(Ku Jing Hao)
    public static JButton getBoardButton(int row, int col) {
        return boardButton[row][col];
    }
    // Getter methods for the "Start" buttons in the view
    //(Ku Jing Hao)
    public JButton getStartButton() {
        return startButton;
    }
    // Getter methods for the "Save" buttons in the view
    //(Ku Jing Hao)
    public JButton getSaveButton() {
        return saveButton;
    }
    // Getter methods for the "Load" buttons in the view
    //(Ku Jing Hao)
    public JButton getLoadButton() {
        return loadButton;
    }
    // Getter methods for the "Restart" buttons in the view
    //(Ku Jing Hao)
    public JButton getRestartButton() {
        return restartButton;
    }
}