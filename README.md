# Talabia Chess

## Project Description
Talabia Chess is a custom chess variant implemented in Java. The game features unique chess pieces with special movement patterns including the Point, Plus, Hourglass, Sun, and Time pieces. Each piece has its own movement rules, creating a strategic gameplay experience different from traditional chess.

The game uses a Model-View-Controller (MVC) architecture and implements several design patterns including Singleton and Observer patterns. Players take turns moving pieces on a 6x7 grid, with blue and yellow teams competing against each other.

## Features
- Custom chess pieces with unique movement capabilities:
  - **Point**: Basic pawn-like piece
  - **Hourglass**: Moves in L-shaped patterns (like a knight in traditional chess)
  - **Plus**: Moves in straight lines
  - **Sun**: Can move one step in any direction
  - **Time**: Special movement piece
- Save and load game functionality
- Turn-based gameplay with alternating blue and yellow players
- Visual board representation with piece icons
- Path validation to ensure legal moves

## Technologies Used
- **Java SE**: Core programming language used for the project
- **Swing**: Used for the graphical user interface (JButtons, JPanel, etc.)
- **AWT**: Used for graphical components (Color, ImageIcon, etc.)
- **Java I/O**: Used for save/load game functionality (FileWriter, PrintWriter, etc.)

## Design Patterns
- **MVC (Model-View-Controller)**: Separates game logic, UI, and controller logic
- **Singleton Pattern**: Used in GameFileManager to ensure only one instance exists
- **Observer Pattern**: Used to notify view of model changes

## Project Structure
- `ChessPiece.java`: Abstract class for all chess pieces
- `ChessboardSetup.java`: Handles the initial setup and updates to the chessboard
- `GameFileManager.java`: Singleton class responsible for saving/loading game states
- Piece Classes:
  - `Hourglass.java`: Represents the Hourglass piece with L-shaped movement
  - `Plus.java`: Represents the Plus piece
  - `Point.java`: Represents the Point piece
  - `Sun.java`: Represents the Sun piece with one-step movement in any direction
  - `Time.java`: Represents the Time piece
- MVC Components:
  - `TalabiaChessModel.java`: Manages the game state and logic
  - `TalabiaChessView.java`: Handles the game's user interface
  - `TalabiaChessController.java`: Manages user input and updates the model/view
- `TalabiaChessMain.java`: Entry point for the application
