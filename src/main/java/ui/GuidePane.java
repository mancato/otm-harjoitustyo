package ui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
* Näkymä pelin ohjeelle.
*/
public class GuidePane extends Pane {
    
    public GuidePane() {
        this.setStyle("-fx-background-color: black;");
        String wallOfText = "*HOW TO PLAY:\n    The objective of the game is to destroy asteroids by\nshooting them. Hitting an asteroid will cause it to break\n into two smaller asteroids, until they completely\ndisappear. If you hit an asteroid with the ship, amount of\nlives is decreased by one. When all asteroids are\ndestroyed,more will appear after a short break.\n\n\n*CONTROLS:\n   Accelerate ship: UP KEY\n   Rotate ship: LEFT/RIGHT KEY\n   Shoot: SPACEBAR\n   Reset Game: R\n   Quit to Menu: Q";
        Text g = new Text(50.0, 50.0, wallOfText);
        g.setFill(Color.WHITE);
        g.setFont(Font.font(Font.getDefault().getName(), 20.0));
        this.getChildren().add(g);        
    }
}
