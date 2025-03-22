# Talabia Chess

## Project Description
Talabia Chess is a custom chess variant implemented in Java. The game features unique chess pieces with special movement patterns including the Point, Plus, Hourglass, Sun, and Time pieces. Each piece has its own movement rules, creating a strategic gameplay experience different from traditional chess.

The game uses a Model-View-Controller (MVC) architecture and implements several design patterns including Singleton and Observer patterns. Players take turns moving pieces on a 6x7 grid, with blue and yellow teams competing against each other.

## Game Rules
![image](https://github.com/user-attachments/assets/0bd9d249-3c7e-4e18-a16b-d0f19f3e835c)

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

## Interface Design
![image](https://github.com/user-attachments/assets/8539d219-680d-4db5-9bd3-65ff068016c1)


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

## Compile and Run Instructions

### Requirements
1. Java version 1.8.0_261 or above
2. Javac 1.8.0_261 or above
3. Ensure compatibility between the Java Runtime Environment (JRE) corresponding to `java` and the Java Development Kit (JDK) corresponding to `javac`.

### To Be Noticed
- To verify the versions of the Java Development Kit (JDK) and Java Runtime Environment (JRE) installed on your system:
  - Run `javac -version`
  - Run `java -version`
  
- If you haven't installed JDK and JRE:
  1. Download and install the Java Development Kit (JDK) and Java Runtime Environment (JRE) from the official Oracle website. During installation, ensure the development tools, including the Java compiler (`javac`), are selected.
  2. Add the `bin` directory of the JDK to the system's PATH variable. Find the path (e.g., `C:\Program Files\Java\jdk1.8.0_391\bin`) and edit the system environment variables accordingly.
  3. Restart the Command Prompt or your terminal for changes to take effect.

### Compilation and Running the Game
1. **Download files**.
2. **Open Command Prompt** (You can use either of the following methods):
   - Open the Start menu and type `terminal` or `command prompt`.
   - Press `Windows key + R`, type `cmd` or `cmd.exe` in the Run command box.
   
3. **Set path to the file** (your file name):
   - Use the `cd` command to navigate to the directory where your Java files are located.
     - Example: `cd YOURFILEPATH`
   - If you mistakenly enter the wrong file path, use `cd..` to navigate back.
   - In case you forget the filename, type `dir` to list all files and folders in the current directory for reference.

4. **Compile and run `TalabiaChessMain.java`** with the following commands:
   - Compile: `javac TalabiaChessMain.java`
   - Run: `java TalabiaChessMain`
