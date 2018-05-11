package ui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
* Näkymä päävalikolle.
* @param hiscore Päävalikossa näkyvä huipputulos.
*/
public class MenuPane extends Pane {
    Text highScore;

    public MenuPane(int hiscore) { // TITLE MENU
        this.setStyle("-fx-background-color: black;");
        Text titleText = new Text(160.0, 100.0, "ASTEROIDS");
        Text play = new Text(280.0, 200.0, "PLAY");
        Text guide = new Text(270.0, 250.0, "GUIDE");
        Text hof = new Text(210.0, 300.0, "HALL OF FAME");
        highScore = new Text(210.0, 400.0, "HIGH SCORE: " + String.valueOf(hiscore));
        titleText.setFill(Color.WHITE);
        titleText.setFont(Font.font(Font.getDefault().getName(), 50.0));
        play.setFill(Color.WHITE);
        play.setFont(Font.font(Font.getDefault().getName(), 30.0));
        guide.setFill(Color.WHITE);
        guide.setFont(Font.font(Font.getDefault().getName(), 30.0));
        hof.setFill(Color.WHITE);
        hof.setFont(Font.font(Font.getDefault().getName(), 30.0));
        highScore.setFill(Color.WHITE);
        highScore.setFont(Font.font(Font.getDefault().getName(), 20.0));
        this.getChildren().addAll(titleText, play, guide, hof, highScore);                        
    }
    /**
    * Asettaa päävalikossa näkyvän high-scoren.
    * @param hiscore Huipputulos.
    */
    public void setHighScore(int hiscore) {
        this.highScore.setText("HIGH SCORE: " + String.valueOf(hiscore));
    }

}
