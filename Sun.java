// Sun class represents a chess piece of type "Sun" on the chessboard.
// It extends the ChessPiece abstract class and overrides the move method
// to allow movements in any direction. Sun pieces can move one step in any direction.
//(Tam Yu Heng)
public class Sun extends ChessPiece {
    private boolean isBlueTurn;
    // Constructor to initialize a Sun piece with a specified position (row, col, isBlueTurn).
    //(Tam Yu Heng)
    public Sun(int row, int col, boolean isBlueTurn) {
        super(row, col);
        this.isBlueTurn = isBlueTurn;
    }

    // Override the move method to implement the specific movement rules for Sun pieces.
    //(Tam Yu Heng)
    @Override
    public boolean move(int newRow, int newCol) {
        // Check if the move is valid (one step in any direction)
        if (Math.abs(newRow - position.getRow()) <= 1 && Math.abs(newCol - position.getCol()) <= 1) {
            return updateMove(newRow, newCol);
        } else {
            System.out.println("Invalid move! Sun piece can only move one step in any direction.");
        }
        return false;  // The move is unsuccessful
    }
    // Override the pieceIcon method to determine the Sun icon path based on movement and game state.
    //(Tam Yu Heng)
    @Override
    public String pieceIcon() {
        // Specific implementation for getting the icon path of Time piece
        return isBlueTurn ? "Piece/Sun_Blue" : "Piece/Sun_Yellow";
    }
}