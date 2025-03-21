import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
// TalabiaChessModel class represents the core logic and data of the Talabia Chess game.
// It includes information about players, game state, and methods for game operations.
// This class follows the Observer pattern, notifying registered observers (such as the view) of state changes.
// (Ku Jing Hao)
public class TalabiaChessModel {
    private Player player1;
    private Player player2;
    private boolean gameStart;
    private static boolean gameOver = false;
    private static boolean isBlueTurn = false;
    private static int moveCount = 0;
    private ChessPiece currentPiece;
    private List<Observer> observers = new ArrayList<>();
    // Lists to keep track of pieces that reached the end
    private List<Integer> blueReachedEnd = new ArrayList<>();
    private List<Integer> yellowReachedEnd = new ArrayList<>();

    // Constructor to initialize players
    //(Ku Jing Hao)
    public TalabiaChessModel() {
        this.player1 = new Player("Yellow", Color.YELLOW);
        this.player2 = new Player("Blue", Color.BLUE);
    }

    // Observer pattern methods
    //(Ku Jing Hao)
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    // Method to set up the initial chessboard pieces
    //(Ku Jing Hao)
    public void setupPiece() {
        ChessboardSetup chessboardSetup = new ChessboardSetup();
        chessboardSetup.initializeChessboard();
    }

    // Method to save the current game state
    //(Ku Jing Hao)
    public void saveGame(String fileName) {
        // Use the singleton instance to save the game
        GameFileManager.getInstance().saveGame(this, fileName);
    }

    // Method to load a saved game state
    //(Ku Jing Hao)
    public void loadGame(String fileName) {
        // Use the singleton instance to load the game
        GameFileManager.getInstance().loadGame(this, fileName);
    }
    
    // Method to select a chess piece on the board
    //(Ku Jing Hao)
    public void selectPiece(int row, int col) {
        // Check if there is no currently selected chess piece or if it's the same turn
        // Check if the clicked button has an icon (chess piece) on it
        if (!getPieceIcon(row, col).isEmpty()) {
            // Check if the clicked piece belongs to the current player
            if ((isBlueTurn && getPieceIcon(row, col).contains("Time_Blue")) ||
                (!isBlueTurn && getPieceIcon(row, col).contains("Time_Yellow"))) {
                // Create a new ChessPiece object representing the selected piece
                currentPiece = new Time(row, col, isBlueTurn);
            } else if ((isBlueTurn && getPieceIcon(row, col).contains("Plus_Blue")) ||
                (!isBlueTurn && getPieceIcon(row, col).contains("Plus_Yellow"))) {
                // Create a new ChessPiece object representing the selected piece
                currentPiece = new Plus(row, col, isBlueTurn);
            } else if ((isBlueTurn && getPieceIcon(row, col).contains("Hourglass_Blue")) ||
                (!isBlueTurn && getPieceIcon(row, col).contains("Hourglass_Yellow"))) {
                // Create a new ChessPiece object representing the selected piece
                currentPiece = new Hourglass(row, col, isBlueTurn);
            } else if ((isBlueTurn && getPieceIcon(row, col).contains("Point_Blue")) ||
                (!isBlueTurn && getPieceIcon(row, col).contains("Point_Yellow"))) {
                // Create a new ChessPiece object representing the selected piece
                currentPiece = new Point(row, col, isBlueTurn, blueReachedEnd, yellowReachedEnd);
            } else if ((isBlueTurn && getPieceIcon(row, col).contains("Sun_Blue")) ||
                (!isBlueTurn && getPieceIcon(row, col).contains("Sun_Yellow"))) {
                // Create a new ChessPiece object representing the selected piece
                currentPiece = new Sun(row, col, isBlueTurn);;
            } else {
                // Print an error message if the selected piece does not belong to the current player
                currentPiece = null;
                System.out.println("Invalid selection. It's not your turn!");
            }
        } else{   
                // Reset the current piece if a button without an icon is clicked
                currentPiece = null;
            }
    }
    
    // Method to move the selected chess piece to a new position
    //(Ku Jing Hao)
    public void movePiece(int newRow, int newCol) {
        // Check if the game is already over
        if (gameOver) {
            System.out.println("Game is over. No more moves allowed.");
            return;
        }
        // Check if there is a currently selected chess piece
        if (currentPiece != null) {
            Position currentSpot = currentPiece.getPosition(); // Get the current position of the selected chess piece
    
            // Check if the new position is the same as the current position
            if (newRow == currentSpot.getRow() && newCol == currentSpot.getCol()) {
                System.out.println("Invalid move! You clicked the same button twice.");
                currentPiece = null; // Reset the currently selected piece
            } else {
                // Check if the chess piece can legally move to the new position
                if (currentPiece.move(newRow, newCol)) {
                    // Handle the successful move
                    handleSuccessfulMove(newRow);
                } else {
                    currentPiece = null; // Reset the currently selected piece
                    System.out.println("Invalid move!");
                }
            }
        }
    }

    // Method to handle a successful move, updating the game state and notifying observers
    //(Ku Jing Hao)
    private void handleSuccessfulMove(int newRow) {
        Player currentPlayer = getCurrentPlayer(); // Get the current player
        determineWinner(); // Check for the winner after each move

        // If the current piece is a Point piece and belongs to the Blue team, rotate its icon
        if (currentPiece.pieceIcon().contains("Point") && currentPlayer.getColor() == Color.BLUE) {
            rotatePointIcon();
        }
    
        // If the current piece is a Point piece and reached the top row, update reachedEnd lists 
        if (currentPiece.pieceIcon().contains("Point")  && newRow == 0) {
            int reachedEndCol = currentPiece.getPosition().getCol();
            if (isBlueTurn) {
                blueReachedEnd.add(reachedEndCol);
            } else {
                yellowReachedEnd.add(reachedEndCol);
            }
            System.out.println("Point piece has reached the end of the row!");
        }
        
        // If the current piece is a Point piece and reached the bottom row, update reachedEnd lists, print messages, and rotate icon
        if (currentPiece.pieceIcon().contains("Point")  && newRow == 5) {
            int reachedEndCol = currentPiece.getPosition().getCol();
            if (isBlueTurn) {
                blueReachedEnd.remove((Integer) reachedEndCol); // Remove the column from the list
            } else {
                yellowReachedEnd.remove((Integer) reachedEndCol); // Remove the column from the list
            }
            System.out.println("Point piece has reached the end of the row!");
    
            // Rotate the Point piece icon
            rotatePointIcon();
        }
    
        // If the game is not over
        if (!gameOver) {
            moveCount++; //update move count
            isBlueTurn = !isBlueTurn; //switch player turns
            if (moveCount % 4 == 0) {
                transformation(); //perform transformations
            }
            currentPiece = null; //reset current piece
            flipScreen(); // flip screen
            notifyObservers(); //notify observers
        }
    }

    // Method to rotate the icon of a Point piece
    //(Ku Jing Hao)
    public void rotatePointIcon()
    {
        int currentRow = currentPiece.getPosition().getRow();
        int currentCol = currentPiece.getPosition().getCol();
        getBoardButton(currentRow, currentCol).setIcon(rotateIcon(getBoardButton(currentRow, currentCol).getIcon()));
    }
    // Method to rotate icons of all pieces on the board
    //(Ku Jing Hao)
     public void rotateAllIcon() {
        if (getIsBlueTurn()) {
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7; col++) {
                    if (isPieceIconPresent(row, col)) {
                         getBoardButton(row, col).setIcon(rotateIcon(getBoardButton(row, col).getIcon()));
                    }
                }
            }
        }
    }
    // Method to flip the screen, changing the perspective of the board
    //(Ku Jing Hao)
     public void flipScreen() {
        // Create a new array to hold the flipped icons
        Icon[][] flippedIcons = new Icon[6][7];
    
        // Access boardButtons through the TalabiaChessView instance
        JButton[][] boardButtons = TalabiaChessView.boardButton;
    
        // Copy the icons from the original positions to the flipped array and rotate them
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Icon originalIcon = boardButtons[row][col].getIcon();
    
                // Rotate the icon if it's not null
                if (originalIcon != null) {
                    ImageIcon rotatedIcon = rotateIcon(originalIcon);
                    flippedIcons[row][col] = rotatedIcon;
                }
            }
        }
    
        // Swap the icons between original and flipped positions
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                boardButtons[row][col].setIcon(flippedIcons[5 - row][6 - col]);
                boardButtons[5 - row][6 - col].setIcon(flippedIcons[row][col]);
            }
        }
    }

     // Method to rotate the given ImageIcon and determine the rotated image filename
     //(Ku Jing Hao)
    private ImageIcon rotateIcon(Icon originalIcon) {
        // Assuming the originalIcon is an instance of ImageIcon
        ImageIcon originalImageIcon = (ImageIcon) originalIcon;
        Image originalImage = originalImageIcon.getImage();
    
        /// Get the dimensions of the original icon, or use default values if not available
        int width = originalImageIcon.getIconWidth() > 0 ? originalImageIcon.getIconWidth() : 100;
        int height = originalImageIcon.getIconHeight() > 0 ? originalImageIcon.getIconHeight() : 100;

        // Create a new BufferedImage with the dimensions of the original icon
        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    
        // Create a Graphics object and draw the rotated image onto the new BufferedImage
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.rotate(Math.PI, rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();
    
        // Determine the rotated image filename based on the original icon filename
        String originalFilename = originalImageIcon.getDescription(); // Assuming the description is set with the filename
    
        // Create a new ImageIcon from the rotated BufferedImage
        return new ImageIcon(rotatedImage, originalFilename);
    }
    
    // Method to perform a transformation on the board after every fourth move
    //(Ku Jing Hao)
    private void transformation() {
    // Iterate through each row on the chessboard
    for (int row = 0; row < 6; row++) {
        // Iterate through each column in the current row
        for (int col = 0; col < 7; col++) {
            // Check if there is a chess piece present at the current position
            if (isPieceIconPresent(row, col)) {
                // Get the current chess piece's icon
                String pieceIcon = getPieceIcon(row, col);
                // If the chess piece is a Blue Time piece
                if (pieceIcon.contains("Time_Blue")) {
                    // Replace the Blue Time piece with a Blue Plus piece
                    placePieceIcon(row, col,"Piece/Plus_Blue");
                } 
                // If the chess piece is a Yellow Time piece
                else if (pieceIcon.contains("Time_Yellow")) {
                    // Replace the Yellow Time piece with a Yellow Plus piece 
                    placePieceIcon(row, col,"Piece/Plus_Yellow");
                } 
                // If the chess piece is a Blue Plus piece 
                else if (pieceIcon.contains("Plus_Blue")) {
                    // Replace the Blue Plus piece with a Blue Time piece
                    placePieceIcon(row, col,"Piece/Time_Blue");
                }
                // If the chess piece is a Yellow Plus piece
                else if (pieceIcon.contains("Plus_Yellow")) {
                    // Replace the Yellow Plus piece with a Yellow Time piece 
                    placePieceIcon(row, col,"Piece/Time_Yellow");
                }
            }
        }
    }
    }
    // Method to clear the chessboard and reset the game state
    //(Ng Min Hoong)
    public void clearChessboard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                removePieceIcon(row, col);
            }
        }
        setMoveCount(0); //Reset move count
        setBlueTurn(false); //Reset Blue Turn to false
        gameOver = false;
        currentPiece = null;
        blueReachedEnd.clear(); // Clear the lists
        yellowReachedEnd.clear(); // Clear the lists
        setupPiece(); 
        notifyObservers();
    }
    // Method to check for the winning condition
    //(Ku Jing Hao, Tam Yu Heng)
    private void determineWinner() {
        Player winner = null;
        boolean blueHasSun = false;
        boolean yellowHasSun = false;
        
        // Check for the winning condition directly in the loop
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                String pieceIcon = getPieceIcon(row, col);
                if (pieceIcon.contains("Sun_Blue")) {
                    blueHasSun = true; // Blue team has Sun piece
                } else if (pieceIcon.contains("Sun_Yellow")) {
                    yellowHasSun = true; // Yellow team has Sun piece
                }
            }
        }
    
        // Determine the winner based on the presence of Sun pieces
        if (!blueHasSun) {
            winner = player1; // Yellow team wins
        } else if (!yellowHasSun) {
            winner = player2; // Blue team wins
        }
    
        // Display the winning message and disable all board buttons if a winner is found
        if (winner != null) {
            JOptionPane.showMessageDialog(null, winner.getName() + " team wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            gameOver = true;
            notifyObservers();
        }
    }
    
    // Method to get the list of columns where Blue Point pieces have reached the end
    //(Ku Jing Hao)
    public List<Integer> getBlueReachedEnd() {
        return blueReachedEnd;
    }
    // Method to get the list of columns where Yellow Point pieces have reached the end
    //(Ku Jing Hao)
    public List<Integer> getYellowReachedEnd() {
        return yellowReachedEnd;
    }
    // Method to set the list of columns where Blue Point pieces have reached the end
    //(Ku Jing Hao)
    public void setBlueReachedEnd(List<Integer> blueReachedEnd) {
        this.blueReachedEnd = blueReachedEnd;
    }
    // Method to set the list of columns where Yellow Point pieces have reached the end
    //(Ku Jing Hao)
    public void setYellowReachedEnd(List<Integer> yellowReachedEnd) {
        this.yellowReachedEnd = yellowReachedEnd;
    }
    
    // Method to check if it is currently the Blue player's turn
    //(Ku Jing Hao)
    public boolean getIsBlueTurn() {
        return this.isBlueTurn;
    }
    
    // Method to get the current player based on the turn (either player1 or player2)
    //(Ku Jing Hao)
    public Player getCurrentPlayer() {
        return getIsBlueTurn() ? player2 : player1;
    }
    
    // Method to get the instance of player1
    //(Ku Jing Hao)
    public Player getPlayer1() {
        return player1;
    }
    
    // Method to get the instance of player2
    //(Ku Jing Hao)
    public Player getPlayer2() {
        return player2;
    }
    // Method to place a chess piece icon on the board
    //(Ku Jing Hao)
    public void placePieceIcon(int row, int col, String imageName) {
        ImageIcon icon = new ImageIcon(imageName + ".png");
        getBoardButton(row, col).setIcon(icon);
    }
    // Method to place a loaded chess piece icon on the board
    //(Ku Jing Hao)
     public void placeLoadPieceIcon(int row, int col, String imageName) {
        ImageIcon icon = new ImageIcon(imageName);
        getBoardButton(row, col).setIcon(icon);
    }
    // Method to remove a chess piece icon from the board
    //(Ku Jing Hao)
    public void removePieceIcon(int row, int col) {
        getBoardButton(row, col).setIcon(null);
    }
     // Method to check if a chess piece icon is present on a specific board button
     //(Ku Jing Hao)
    public boolean isPieceIconPresent(int row, int col) {
        return getBoardButton(row, col).getIcon() != null;  // Check if the icon of the specified board button is not null
    }
    // Method to get the string representation of the chess piece icon on a specific board button
    //(Ku Jing Hao)
    public String getPieceIcon(int row, int col) {
        Icon icon = getBoardButton(row, col).getIcon(); // Get the icon of the specified board button
        return (icon != null) ? icon.toString() : ""; // Return the string representation of the icon, or an empty string if the icon is null
    }
    // Getter methods for game state
    //(Ku Jing Hao)
    public boolean getGameStart() {
        return gameStart;
    }
    // Setter method for game state
    //(Ku Jing Hao)
    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }
    // Setter method for the current player's turn
    //(Ku Jing Hao)
    public void setBlueTurn(boolean isBlueTurn) {
        this.isBlueTurn = isBlueTurn;
    }
    // Getter method for the number of moves made in the game
    //(Ku Jing Hao)
    public int getMoveCount(){
        return moveCount;
    }
    // Setter method for the number of moves
    //(Ku Jing Hao)
    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
    // Getter method for the currently selected chess piece
    //(Ku Jing Hao)
     public ChessPiece getCurrentPiece() {
        return currentPiece;
    }
    // Getter method to access the board button at a specific row and column
    //(Ku Jing Hao)
     public JButton getBoardButton(int row, int col) {
        return TalabiaChessView.getBoardButton(row, col);
    }
     // Getter method for the game over status
     //(Ku Jing Hao)
    public boolean getGameOver(){
        return gameOver;
    }
}