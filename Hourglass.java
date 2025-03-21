// Hourglass class represents a chess piece of type "Hourglass" on the chessboard.
// It extends the ChessPiece abstract class and overrides the move method
// to allow L-shaped movements. Hourglass pieces move in an L-shaped pattern.
//(Ng Min Hoong)
public class Hourglass extends ChessPiece {
    private boolean isBlueTurn;
    // Constructor to initialize an Hourglass piece with a specified position (row, col, isBlueTurn).
    //(Ng Min Hoong)
    public Hourglass(int row, int col, boolean isBlueTurn) {
        super(row, col);
        this.isBlueTurn = isBlueTurn;
    }

    // Override the move method to implement the specific movement rules for Hourglass pieces.
    //(Ng Min Hoong)
    @Override
    public boolean move(int newRow, int newCol) {
        // Check if the move is an L-shaped move
        if ((Math.abs(newRow - position.getRow()) == 2 && Math.abs(newCol - position.getCol()) == 1) ||
            (Math.abs(newRow - position.getRow()) == 1 && Math.abs(newCol - position.getCol()) == 2)) {
            return updateMove(newRow, newCol);
        } else {
            System.out.println("Invalid move! Hourglass piece moves in an L-shaped pattern.");
        }
        return false;  // The move is unsuccessful
    }
    // Override the pieceIcon method to determine the Hourglass icon path based on movement and game state.
    //(Ng Min Hoong)
    @Override
    public String pieceIcon() {
        // Specific implementation for getting the icon path of Time piece
        return isBlueTurn ? "Piece/Hourglass_Blue" : "Piece/Hourglass_Yellow";
    }
}