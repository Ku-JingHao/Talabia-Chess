// Time class represents a chess piece of type "Time" on the chessboard.
// It extends the ChessPiece abstract class and overrides the move method
// to allow diagonal movements. Time pieces can move diagonally on the board.
//(Wan Aqel Hakimi Bin Mohd Zamri)
public class Time extends ChessPiece {
    private boolean isBlueTurn;
    // Constructor to initialize a Time piece with a specified position (row, col).
    //(Wan Aqel Hakimi Bin Mohd Zamri)
    public Time(int row, int col, boolean isBlueTurn) {
        super(row, col);
        this.isBlueTurn = isBlueTurn;
    }

    // Override the move method to implement the specific movement rules for Time pieces.
    //(Wan Aqel Hakimi Bin Mohd Zamri)
    @Override
    public boolean move(int newRow, int newCol) {
        // Check if the move is a valid diagonal move
        if (Math.abs(newRow - position.getRow()) == Math.abs(newCol - position.getCol())) {
            return updateMove(newRow, newCol);
        } else {
            System.out.println("Invalid move! Time piece can only move diagonally.");
        }
        return false;  // The move is unsuccessful
    }
    // Override the pieceIcon method to determine the Time icon path based on movement and game state.
    //(Wan Aqel Hakimi Bin Mohd Zamri)
    @Override
    public String pieceIcon() {
        // Specific implementation for getting the icon path of Time piece
        return isBlueTurn ? "Piece/Time_Blue" : "Piece/Time_Yellow";
    }
}