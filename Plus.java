// Plus class represents a chess piece of type "Plus" on the chessboard.
// It extends the ChessPiece abstract class and overrides the move method
// to allow horizontal or vertical movements. Plus pieces can move in straight lines.
//(See Jian Man)
public class Plus extends ChessPiece {
    private boolean isBlueTurn;
    // Constructor to initialize a Plus piece with a specified position (row, col, isBlueTurn).
    //(See Jian Man)
    public Plus(int row, int col, boolean isBlueTurn) {
        super(row, col);
        this.isBlueTurn = isBlueTurn;
    }

    // Override the move method to implement the specific movement rules for Plus pieces.
    //(See Jian Man)
    @Override
    public boolean move(int newRow, int newCol) {
        // Check if the move is either horizontal or vertical
        if ((newRow == position.getRow() && newCol != position.getCol()) ||
            (newRow != position.getRow() && newCol == position.getCol())) {
            return updateMove(newRow, newCol);
        } else {
            System.out.println("Invalid move! Plus piece can only move horizontally or vertically.");
        }
        return false;  // The move is unsuccessful
    }
    // Override the pieceIcon method to determine the Plus icon path based on movement and game state.
    //(See Jian Man)
    @Override
    public String pieceIcon() {
        // Specific implementation for getting the icon path of Time piece
        return isBlueTurn ? "Piece/Plus_Blue" : "Piece/Plus_Yellow";
    }
}