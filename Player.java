import javax.swing.*;
import java.awt.*;
// The Player class represents a participant in the Talabia Chess game.
// It encapsulates essential properties such as the player's name and chess piece color.
//(Ku Jing Hao)
public class Player {
    private String name;  // The name of the player.
    private Color color;  // The color associated with the player's chess pieces.

    // Constructor to initialize a Player with a specified name and color.
    //(Ku Jing Hao)
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    // Getter method to retrieve the name of the player.
    //(Ku Jing Hao)
    public String getName() {
        return name;
    }

    // Getter method to retrieve the color associated with the player's chess pieces.
    //(Ku Jing Hao)
    public Color getColor() {
        return color;
    }
}
