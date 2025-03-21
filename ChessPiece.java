// ChessPiece is an abstract class representing a generic chess piece in the game.
// It serves as the base class for specific chess piece types, providing common properties and methods.
// The class includes a Position object to track the current position of the chess piece on the chessboard,
// and a ChessboardSetup object to interact with the chessboard and perform moves.
//(Ku Jing Hao)
public abstract class ChessPiece {
    protected Position position;  // Represents the current position of the chess piece.
    protected ChessboardSetup chessboard;  // Provides functionality to interact with the chessboard.
    
    // Constructor to initialize a ChessPiece with a specified initial position (row, col).
    //(Ku Jing Hao)
    public ChessPiece(int row, int col) {
        this.position = new Position(row, col);
        this.chessboard = new ChessboardSetup();  // Initialize the chessboard setup.
    }

    // Getter method to retrieve the current position of the chess piece.
    //(Ku Jing Hao)
    public Position getPosition() {
        return position;
    }
    
    // Method to perform moves using updateBoard, updating the piece position accordingly.
    //(Ku Jing Hao)
    protected boolean updateMove(int newRow, int newCol) {
        return chessboard.updateBoard(this, newRow, newCol);
    }
    
    // Abstract method to be implemented by specific chess piece classes for movement rules.
    //(Ku Jing Hao)
    public abstract boolean move(int newRow, int newCol);
    // Abstract method to be implemented by specific chess piece classes for obtaining the piece's icon path.
    //(Ku Jing Hao)
    public abstract String pieceIcon();
}
