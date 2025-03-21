// Position class represents a position on the chessboard with a specific row and column.
// It is used to store and retrieve the coordinates of chess pieces on the board.
// This simple class provides getters and setters for both row and column properties.
//(Ku Jing Hao)
public class Position {
    // The row and column coordinates of the position.
    private int row;
    private int col;
    
    // Constructor to initialize a Position with specified row and column.
    //(Ku Jing Hao)
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Getter method to retrieve the row coordinate.
    //(Ku Jing Hao)
    public int getRow() {
        return row;
    }

    // Setter method to update the row coordinate.
    //(Ku Jing Hao)
    public void setRow(int row) {
        this.row = row;
    }

    // Getter method to retrieve the column coordinate.
    //(Ku Jing Hao)
    public int getCol() {
        return col;
    }

    // Setter method to update the column coordinate.
    //(Ku Jing Hao)
    public void setCol(int col) {
        this.col = col;
    }
}