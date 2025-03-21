import javax.swing.*;
import java.awt.*;
// ChessboardSetup class handles the initial setup of the chessboard by placing pieces on it.
// It encapsulates the logic for placing different types of pieces on the board.
//(Ku Jing Hao)
public class ChessboardSetup{
    private TalabiaChessModel model = new TalabiaChessModel();
    
    // Method to initialize the chessboard with the initial arrangement of pieces
    //(Ku Jing Hao)
    public void initializeChessboard() {
        System.out.println("Initialize The Chessboard With Initial Pieces");
        // Set up the chess board with initial pieces
        placePieceIcon(0, 2, "Piece/Time", model.getPlayer1().getColor());
        placePieceIcon(0, 4, "Piece/Time", model.getPlayer1().getColor());
        placePieceIcon(5, 2, "Piece/Time", model.getPlayer2().getColor());
        placePieceIcon(5, 4, "Piece/Time", model.getPlayer2().getColor());

        placePieceIcon(1, 0, "Piece/Point", model.getPlayer1().getColor());
        placePieceIcon(1, 1, "Piece/Point", model.getPlayer1().getColor());
        placePieceIcon(1, 2, "Piece/Point", model.getPlayer1().getColor());
        placePieceIcon(1, 3, "Piece/Point", model.getPlayer1().getColor());
        placePieceIcon(1, 4, "Piece/Point", model.getPlayer1().getColor());
        placePieceIcon(1, 5, "Piece/Point", model.getPlayer1().getColor());
        placePieceIcon(1, 6, "Piece/Point", model.getPlayer1().getColor());
        placePieceIcon(4, 0, "Piece/Point", model.getPlayer2().getColor());
        placePieceIcon(4, 1, "Piece/Point", model.getPlayer2().getColor());
        placePieceIcon(4, 2, "Piece/Point", model.getPlayer2().getColor());
        placePieceIcon(4, 3, "Piece/Point", model.getPlayer2().getColor());
        placePieceIcon(4, 4, "Piece/Point", model.getPlayer2().getColor());
        placePieceIcon(4, 5, "Piece/Point", model.getPlayer2().getColor());
        placePieceIcon(4, 6, "Piece/Point", model.getPlayer2().getColor());

        placePieceIcon(0, 0, "Piece/Plus", model.getPlayer1().getColor());
        placePieceIcon(0, 6, "Piece/Plus", model.getPlayer1().getColor());
        placePieceIcon(5, 0, "Piece/Plus", model.getPlayer2().getColor());
        placePieceIcon(5, 6, "Piece/Plus", model.getPlayer2().getColor());

        placePieceIcon(0, 3, "Piece/Sun", model.getPlayer1().getColor());
        placePieceIcon(5, 3, "Piece/Sun", model.getPlayer2().getColor());

        placePieceIcon(0, 1, "Piece/Hourglass", model.getPlayer1().getColor());
        placePieceIcon(0, 5, "Piece/Hourglass", model.getPlayer1().getColor());
        placePieceIcon(5, 1, "Piece/Hourglass", model.getPlayer2().getColor());
        placePieceIcon(5, 5, "Piece/Hourglass", model.getPlayer2().getColor());
    }

    // Method to place a piece icon on the specified row and column
    //(Ku Jing Hao)
    private void placePieceIcon(int row, int col, String pieceType, Color color) {
        String playerColor = color.equals(model.getPlayer1().getColor()) ? "Blue" : "Yellow";
        ImageIcon icon = new ImageIcon(pieceType + "_" + playerColor + ".png");
        model.getBoardButton(row, col).setIcon(icon);
    }

    // Method to update the chessboard after a piece moves
    //(Ku Jing Hao)
    public boolean updateBoard(ChessPiece piece, int newRow, int newCol) {
        if (isPathClear(piece, newRow, newCol)) {
            if (isDestinationValid(newRow, newCol)) {
                model.removePieceIcon(piece.getPosition().getRow(), piece.getPosition().getCol());
                piece.getPosition().setRow(newRow);
                piece.getPosition().setCol(newCol);
                model.placePieceIcon(newRow, newCol, piece.pieceIcon());
                return true;
            } else {
                System.out.println("Invalid move! The destination is occupied by the same color.");
            }
        } else {
            System.out.println("Invalid move! The path is not clear.");
        }
        return false;
    }
    
    // Method to check if the destination is a valid move
    //(Ku Jing Hao, Ng Min Hoong)
    public boolean isDestinationValid(int newRow, int newCol) {
        // Check if the destination is either empty or has an opponent's piece
        return !model.isPieceIconPresent(newRow, newCol) ||
                (model.getIsBlueTurn() && model.getPieceIcon(newRow, newCol).contains("Yellow")) ||
                (!model.getIsBlueTurn() && model.getPieceIcon(newRow, newCol).contains("Blue"));
    }
    
    // Method to check if the path between the current and new position is clear
    //(Ku Jing Hao)
    public boolean isPathClear(ChessPiece piece, int newRow, int newCol) {
        // Determine the direction of the move
        int rowStep = Integer.compare(newRow, piece.getPosition().getRow());
        int colStep = Integer.compare(newCol, piece.getPosition().getCol());

        // Initialize the current position to the next position in the move
        int currentRow = piece.getPosition().getRow() + rowStep;
        int currentCol = piece.getPosition().getCol() + colStep;

        // Iterate over the path to check if it is clear
        while (currentRow != newRow || currentCol != newCol) {
            // Check if there is an icon (chess piece) in the way
            if(piece.pieceIcon().contains("Hourglass"))  //if the piece is Hourglass
            {
                return true; // return true because Hourglass is the only piece that can skip over other pieces
            }
            
            if (model.isPieceIconPresent(currentRow, currentCol)) {
                System.out.println("Path is not clear!"); // The path is not clear
                return false;
            }

            // Move to the next position in the path
            currentRow += rowStep;
            currentCol += colStep;
        }

        return true;  // The path is clear
    }
}