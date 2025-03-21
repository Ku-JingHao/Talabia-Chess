import java.util.ArrayList;
import java.util.List;
// Point class represents a chess piece of type "Point" on the chessboard.
// It extends the ChessPiece abstract class and overrides the move method
// to allow forward movements. Point pieces can move 1 or 2 steps forward.
// Additionally, Point pieces have specific conditions for moving forward based on the game state.
//(Ku Jing Hao)
public class Point extends ChessPiece {
    private boolean movingBackward;
    private boolean isBlueTurn;
    private List<Integer> blueReachedEnd, yellowReachedEnd;
    // Constructor to initialize a Point piece with specified position, color, and additional game state information.
    //(Ku Jing Hao)
    public Point(int row, int col, boolean isBlueTurn, List<Integer> blueReachedEnd, List<Integer> yellowReachedEnd) {
        super(row, col);
        this.isBlueTurn = isBlueTurn;
        this.blueReachedEnd = new ArrayList<>(blueReachedEnd);  
        this.yellowReachedEnd = new ArrayList<>(yellowReachedEnd);  
        this.movingBackward = true;
    }
    
    // Override the move method to implement the specific movement rules for Point pieces.
    //(Ku Jing Hao)
    @Override
    public boolean move(int newRow, int newCol) {
        // Check if the move is either 1 or 2 steps forward based on game conditions
        int rowDifference = newRow - position.getRow();
        int colDifference = newCol - position.getCol();
        // Check if the move is either 1 or 2 steps forward
        if (((isBlueTurn && Math.abs(rowDifference) == 1 && colDifference == 0 && rowDifference < 0 && (!blueReachedEnd.contains(position.getCol()))) || (!isBlueTurn && Math.abs(rowDifference) == 1 && colDifference == 0 && rowDifference < 0 && (!yellowReachedEnd.contains(position.getCol()))))
        || ((isBlueTurn && Math.abs(rowDifference) == 2 && colDifference == 0 && rowDifference < 0 && (!blueReachedEnd.contains(position.getCol()))) || ( !isBlueTurn && Math.abs(rowDifference) == 2 && colDifference == 0 && rowDifference < 0 && (!yellowReachedEnd.contains(position.getCol()))))
        || ((isBlueTurn&& Math.abs(rowDifference) == 1 && colDifference == 0 && rowDifference > 0 && blueReachedEnd.contains(position.getCol())) || ( !isBlueTurn && Math.abs(rowDifference) == 1 && colDifference == 0 && rowDifference > 0 && yellowReachedEnd.contains(position.getCol())))
        || ((isBlueTurn && Math.abs(rowDifference) == 2 && colDifference == 0 && rowDifference > 0 && blueReachedEnd.contains(position.getCol())) || (!isBlueTurn && Math.abs(rowDifference) == 2 && colDifference == 0 && rowDifference > 0 && yellowReachedEnd.contains(position.getCol())))
        ) {
             // Additional conditions and logic for moving backward
            if (((movingBackward && rowDifference > 0 && colDifference == 0) || position.getRow() == 0 )) {
                setMovingBackward(false);
            }
            return updateMove(newRow, newCol);
        } else{
            System.out.println("Invalid move! Point piece can only move forward, 1 or 2 steps.");
            return false; // The move is unsuccessful
        }
    }
    
    // Getter method to check if the Point piece is currently moving backward
    //(Ku Jing Hao)
    public boolean isMovingBackward() {
        return movingBackward;
    }
    
    // Setter method to update the movingForward property of the Point piece.
    //(Ku Jing Hao)
    public void setMovingBackward(boolean movingBackward) {
        this.movingBackward = movingBackward;
    }
    // Override the pieceIcon method to determine the Point icon path based on movement and game state.
    //(Ku Jing Hao)
    @Override
    public String pieceIcon() {
        int rowDifference = getPosition().getRow() - 0; // Check if the piece is at the top row
        boolean isMovingBackward = isMovingBackward(); // Assuming Point class has a method to check if it's moving forward
        if (isMovingBackward &&  rowDifference > 0) {
              return isBlueTurn ? "Piece/Point_Blue" : "Piece/Point_Yellow";
        } else {
            return isBlueTurn ? "Piece/rotatedPoint_Blue" : "Piece/rotatedPoint_Yellow";
        }
    }
}